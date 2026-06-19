package com.liuyuheng.sgdata.carparkavailability.domain.repositories

import com.liuyuheng.sgdata.carparkavailability.data.models.CarparkAvailabilityDto.CarparkAvailabilityData.CarparkData
import com.liuyuheng.sgdata.core.domain.models.ApiResult

interface CarparkAvailabilityRepository {

    suspend fun getCarparkAvailability(): ApiResult<List<CarparkData>>
}