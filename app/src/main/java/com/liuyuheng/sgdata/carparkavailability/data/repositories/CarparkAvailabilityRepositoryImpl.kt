package com.liuyuheng.sgdata.carparkavailability.data.repositories

import com.liuyuheng.sgdata.carparkavailability.data.api.CarparkAvailabilityApi
import com.liuyuheng.sgdata.carparkavailability.data.models.CarparkAvailabilityDto.CarparkAvailabilityData.CarparkData
import com.liuyuheng.sgdata.carparkavailability.domain.repositories.CarparkAvailabilityRepository
import com.liuyuheng.sgdata.core.data.network.safeApiCall
import com.liuyuheng.sgdata.core.domain.models.ApiResult
import javax.inject.Inject

class CarparkAvailabilityRepositoryImpl @Inject constructor(
    private val carparkAvailabilityApi: CarparkAvailabilityApi,
) : CarparkAvailabilityRepository {

    override suspend fun getCarparkAvailability(): ApiResult<List<CarparkData>> {
        return safeApiCall {
            val carparkAvailabilityResponse = carparkAvailabilityApi.getCarparkAvailability()
            carparkAvailabilityResponse.items.first().carparkData
        }
    }
}