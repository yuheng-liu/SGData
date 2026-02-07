package com.liuyuheng.sgdata.data.repository

import com.liuyuheng.sgdata.data.database.MetadataDao
import com.liuyuheng.sgdata.data.repository.CarparkInfoRepositoryImpl.Companion.CARPARK_INFO_LAST_UPDATED
import com.liuyuheng.sgdata.domain.repository.MetadataRepository
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
}