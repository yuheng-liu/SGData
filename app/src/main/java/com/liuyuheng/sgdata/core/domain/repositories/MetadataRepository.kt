package com.liuyuheng.sgdata.core.domain.repositories

import java.time.LocalDateTime

interface MetadataRepository {

    suspend fun getCarparkInfoLastUpdated(): LocalDateTime?
    suspend fun addMetaData(key: String, value: String)
}