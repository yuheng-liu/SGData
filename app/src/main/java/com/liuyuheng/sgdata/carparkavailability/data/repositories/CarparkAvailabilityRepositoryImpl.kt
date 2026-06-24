package com.liuyuheng.sgdata.carparkavailability.data.repositories

import androidx.room.withTransaction
import com.liuyuheng.sgdata.carparkavailability.data.api.CarparkAvailabilityApi
import com.liuyuheng.sgdata.carparkavailability.data.mappers.toDomain
import com.liuyuheng.sgdata.carparkavailability.data.models.CarparkAvailabilityDto.CarparkAvailabilityData.CarparkData
import com.liuyuheng.sgdata.carparkavailability.data.models.CarparkInfoDao
import com.liuyuheng.sgdata.carparkavailability.data.models.CarparkInfoEntity
import com.liuyuheng.sgdata.carparkavailability.domain.models.CarparkInfo
import com.liuyuheng.sgdata.carparkavailability.domain.models.CarparkInfo.Coordinates
import com.liuyuheng.sgdata.carparkavailability.domain.models.CarparkLotType
import com.liuyuheng.sgdata.carparkavailability.domain.models.CarparkType
import com.liuyuheng.sgdata.carparkavailability.domain.models.ParkingSystemType
import com.liuyuheng.sgdata.carparkavailability.domain.repositories.CarparkAvailabilityRepository
import com.liuyuheng.sgdata.core.data.database.SGDataDatabase
import com.liuyuheng.sgdata.core.data.models.ApiResult
import com.liuyuheng.sgdata.core.data.network.api.DatasetDownloadApi
import com.liuyuheng.sgdata.core.data.network.safeApiCall
import com.liuyuheng.sgdata.core.data.repositories.MetadataRepositoryImpl.Companion.CARPARK_INFO_LAST_UPDATED
import com.liuyuheng.sgdata.core.di.ApplicationScope
import com.liuyuheng.sgdata.core.domain.models.DomainResult
import com.liuyuheng.sgdata.core.domain.repositories.MetadataRepository
import com.liuyuheng.sgdata.core.utils.Constants.CARPARK_INFO_DOWNLOAD_DAYS_THRESHOLD
import com.liuyuheng.sgdata.core.utils.hasTimePassedSince
import com.liuyuheng.sgdata.core.utils.toBooleanOrNull
import com.opencsv.CSVReader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.io.InputStream
import java.io.InputStreamReader
import java.time.Duration
import java.time.LocalDateTime
import javax.inject.Inject

/**
 * Repository for setting up carpark availability data
 *
 * 1. Check if the database is up to date using MetadataRepository
 * 2. Download the updated details of general carpark information using DatasetDownloadApi
 * 3. Download the updated carpark availability data using CarparkAvailabilityApi
 * 4. Combine the two datasets into a single list of CarparkInfo objects
 *
 * @property datasetDownloadApi API for downloading datasets
 * @property carparkAvailabilityApi API for getting carpark availability data
 * @property metadataRepository Repository for storing/retrieving metadata
 */

class CarparkAvailabilityRepositoryImpl @Inject constructor(
    private val datasetDownloadApi: DatasetDownloadApi,
    private val carparkAvailabilityApi: CarparkAvailabilityApi,
    private val metadataRepository: MetadataRepository,
    private val sgDataDatabase: SGDataDatabase,
    private val carparkInfoDao: CarparkInfoDao,
    @ApplicationScope private val appScope: CoroutineScope
) : CarparkAvailabilityRepository {

    init {
        appScope.launch(Dispatchers.IO) {
            // update carpark info database if it has been more than a week
            metadataRepository.getCarparkInfoLastUpdated()?.let { lastUpdated ->
                if (hasTimePassedSince(lastUpdated, Duration.ofDays(CARPARK_INFO_DOWNLOAD_DAYS_THRESHOLD))) {
                    updateCarparkDetailsDataset()
                }
            }
        }
    }

    private val _carparkAvailabilityApiTrigger = MutableSharedFlow<Unit>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    @OptIn(ExperimentalCoroutinesApi::class)
    private val carparkAvailabilityApiStream = _carparkAvailabilityApiTrigger.flatMapLatest {
        flow { emit(safeApiCall { carparkAvailabilityApi.getCarparkAvailability() }) }
    }

    override val carparkAvailabilityDataStream = combine(
        carparkInfoDao.getCarparkInfoStream(),
        carparkAvailabilityApiStream,
    ) { carparkInfoEntities, carparkAvailabilityResult ->
        when (carparkAvailabilityResult) {
            is ApiResult.Success -> {
                DomainResult.Success(
                    mergeCarparkInfoAndAvailability(
                        carparkInfoEntities,
                        carparkAvailabilityResult.data.items.first().carparkData
                    )
                )
            }

            is ApiResult.Error.HttpError -> DomainResult.Failure.NoData(carparkAvailabilityResult.message)
            is ApiResult.Error.NetworkError -> DomainResult.Failure.NoData(carparkAvailabilityResult.message)
            is ApiResult.Error.UnknownError -> DomainResult.Failure.Unavailable(carparkAvailabilityResult.message)
        }
    }

    override fun updateCarparkDetailsDataset() {
        appScope.launch(Dispatchers.IO) {
            val downloadResponse = datasetDownloadApi.initiateCarparkDetailsDownload()
            downloadResponse.data?.let { downloadData ->
                // returned url points to a CSV file containing all carpark info
                val response = datasetDownloadApi.downloadFromUrl(downloadData.url)
                if (response.isSuccessful) {
                    response.body()?.byteStream()?.use { stream ->
                        sgDataDatabase.withTransaction {
                            metadataRepository.addMetaData(
                                key = CARPARK_INFO_LAST_UPDATED,
                                value = LocalDateTime.now().toString()
                            )
                            carparkInfoDao.clearCarparkInfo()
                            carparkInfoDao.insertAll(parseCsv(stream))
                        }
                    }
                }
            }
        }
    }

    override fun getCarparkDetailsDataset(): Flow<List<CarparkInfo>> =
        carparkInfoDao.getCarparkInfoStream().map { entities -> entities.map { it.toDomain() } }

    override suspend fun fetchCarparkAvailability() {
        _carparkAvailabilityApiTrigger.emit(Unit)
    }

    private fun parseCsv(inputStream: InputStream): List<CarparkInfoEntity> {
        val reader = CSVReader(InputStreamReader(inputStream))
        val rows = reader.readAll().drop(1)

        return rows.map { rows ->
            CarparkInfoEntity(
                carparkNumber = rows[0],
                address = rows[1],
                xCoordinate = rows[2].toDoubleOrNull() ?: 0.0,
                yCoordinate = rows[3].toDoubleOrNull() ?: 0.0,
                carparkType = CarparkType.fromValue(rows[4]),
                typeOfParkingSystem = ParkingSystemType.fromValue(rows[5]),
                shortTermParking = rows[6],
                freeParking = rows[7],
                nightParking = rows[8].toBooleanOrNull() ?: false,
                carparkDecks = rows[9].toIntOrNull() ?: 0,
                gantryHeight = rows[10].toDoubleOrNull() ?: 0.0,
                carparkBasement = rows[11].toBooleanOrNull() ?: false
            )
        }
    }

    private fun mergeCarparkInfoAndAvailability(entities: List<CarparkInfoEntity>, availabilities: List<CarparkData>): List<CarparkInfo> {
        // convert to map for O(1) lookups
        val availabilitiesMap = availabilities.associateBy { it.carparkNumber }

        return entities.map { entity ->
            val availability = availabilitiesMap[entity.carparkNumber]

            CarparkInfo(
                carparkId = entity.carparkNumber,
                address = entity.address,
                coordinates = Coordinates(entity.xCoordinate, entity.yCoordinate),
                carparkType = entity.carparkType,
                parkingSystemType = entity.typeOfParkingSystem,
                shortTermParkingTiming = entity.shortTermParking,
                freeParkingTiming = entity.freeParking,
                nightParkingAvailable = entity.nightParking,
                carparkDecks = entity.carparkDecks,
                gantryHeight = entity.gantryHeight,
                carparkBasementAvailable = entity.carparkBasement,
                lotsAvailability = availability?.carparkAvailabilityInfo?.map { availabilityInfo ->
                    CarparkInfo.LotsAvailability(
                        totalLots = availabilityInfo.totalLots.toIntOrNull() ?: 0,
                        lotType = CarparkLotType.entries.find { it.code == availabilityInfo.lotType } ?: CarparkLotType.UNKNOWN,
                        availableLots = availabilityInfo.lotsAvailable.toIntOrNull() ?: 0
                    )
                }
            )
        }
    }
}