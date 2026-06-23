package com.liuyuheng.sgdata.carparkavailability.domain.repositories

import com.liuyuheng.sgdata.carparkavailability.data.models.CarparkAvailabilityDto
import com.liuyuheng.sgdata.carparkavailability.domain.models.CarparkInfo
import com.liuyuheng.sgdata.core.domain.models.ApiResult
import kotlinx.coroutines.flow.Flow

interface CarparkAvailabilityRepository {

    fun updateCarparkInfoDataset()
    fun getCarparkInfoDataset(): Flow<List<CarparkInfo>>
    fun getCarparkAvailabilityStream(): Flow<ApiResult<CarparkAvailabilityDto>>
}