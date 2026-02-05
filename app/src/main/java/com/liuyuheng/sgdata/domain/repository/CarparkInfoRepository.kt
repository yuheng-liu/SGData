package com.liuyuheng.sgdata.domain.repository

import com.liuyuheng.sgdata.domain.model.carpark.CarparkInfo
import kotlinx.coroutines.flow.Flow

interface CarparkInfoRepository {

    suspend fun updateCarparkInfoDataset()

    fun getCarparkInfoDataset(): Flow<List<CarparkInfo>>
}