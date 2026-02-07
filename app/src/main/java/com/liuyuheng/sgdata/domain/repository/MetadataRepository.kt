package com.liuyuheng.sgdata.domain.repository

import java.time.LocalDateTime

interface MetadataRepository {

    suspend fun getCarparkInfoLastUpdated(): LocalDateTime?
}