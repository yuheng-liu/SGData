package com.liuyuheng.sgdata.core.data.repositories

import com.liuyuheng.sgdata.core.data.database.models.metadata.MetadataDao
import com.liuyuheng.sgdata.core.data.database.models.metadata.MetadataEntity
import com.liuyuheng.sgdata.core.domain.repositories.MetadataRepository
import java.time.LocalDateTime
import javax.inject.Inject

class MetadataRepositoryImpl @Inject constructor(
    private val metadataDao: MetadataDao
) : MetadataRepository {

    override suspend fun getCarparkInfoLastUpdated(): LocalDateTime? {
        return metadataDao.getValue(CARPARK_INFO_LAST_UPDATED)?.let {
            LocalDateTime.parse(it)
        }
    }

    override suspend fun addMetaData(key: String, value: String) {
        metadataDao.insert(MetadataEntity(key = key, value = value))
    }

    companion object {
        const val CARPARK_INFO_LAST_UPDATED = "CARPARK_INFO_LAST_UPDATED"
    }
}