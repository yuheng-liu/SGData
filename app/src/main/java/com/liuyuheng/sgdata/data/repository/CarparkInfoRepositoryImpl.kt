package com.liuyuheng.sgdata.data.repository

import com.liuyuheng.sgdata.data.database.CarparkInfoDao
import com.liuyuheng.sgdata.data.database.CarparkInfoEntity
import com.liuyuheng.sgdata.data.model.mappers.toDomain
import com.liuyuheng.sgdata.data.network.DatasetDownloadApi
import com.liuyuheng.sgdata.data.toBooleanOrNull
import com.liuyuheng.sgdata.domain.model.carpark.CarparkInfo
import com.liuyuheng.sgdata.domain.model.carpark.CarparkType
import com.liuyuheng.sgdata.domain.model.carpark.ParkingSystemType
import com.liuyuheng.sgdata.domain.repository.CarparkInfoRepository
import com.opencsv.CSVReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import javax.inject.Inject

class CarparkInfoRepositoryImpl @Inject constructor(
    private val datasetDownloadApi: DatasetDownloadApi,
    private val carparkInfoDao: CarparkInfoDao
) : CarparkInfoRepository {

    override suspend fun updateCarparkInfoDataset() {
        withContext(Dispatchers.IO) {
            val downloadResponse = datasetDownloadApi.initiateCarparkInfoDownload()
            downloadResponse.data?.let { downloadData ->
                // returned url points to a CSV file containing all carpark info
                val response = datasetDownloadApi.downloadFromUrl(downloadData.url)
                if (response.isSuccessful) {
                    response.body()?.byteStream()?.use { stream ->
                        carparkInfoDao.insertAll(parseCsv(stream))
                    }
                }
            }
        }
    }

    override fun getCarparkInfoDataset(): Flow<List<CarparkInfo>> =
        carparkInfoDao.getCarparkInfoStream().map { entities -> entities.map { it.toDomain() } }

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
}