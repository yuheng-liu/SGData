package com.liuyuheng.sgdata.carparkavailability.domain.repositories

import com.liuyuheng.sgdata.carparkavailability.domain.models.CarparkInfo
import kotlinx.coroutines.flow.Flow

interface CarparkInfoRepository {

    suspend fun updateCarparkInfoDataset()

    fun getCarparkInfoDataset(): Flow<List<CarparkInfo>>

    suspend fun ensureDatabaseUpToDate()
}