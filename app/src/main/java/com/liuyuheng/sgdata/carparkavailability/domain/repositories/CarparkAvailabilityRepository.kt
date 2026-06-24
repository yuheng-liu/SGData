package com.liuyuheng.sgdata.carparkavailability.domain.repositories

import com.liuyuheng.sgdata.carparkavailability.domain.models.CarparkInfo
import com.liuyuheng.sgdata.core.domain.models.DomainResult
import kotlinx.coroutines.flow.Flow

interface CarparkAvailabilityRepository {

    val carparkAvailabilityDataStream: Flow<DomainResult<List<CarparkInfo>>>

    fun updateCarparkDetailsDataset()
    fun getCarparkDetailsDataset(): Flow<List<CarparkInfo>>
    suspend fun fetchCarparkAvailability()
}