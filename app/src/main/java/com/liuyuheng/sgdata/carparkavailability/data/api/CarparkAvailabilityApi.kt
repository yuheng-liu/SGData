package com.liuyuheng.sgdata.carparkavailability.data.api

import com.liuyuheng.sgdata.carparkavailability.data.models.CarparkAvailabilityDto
import retrofit2.http.GET
import retrofit2.http.Query

private const val CARPARK_AVAILABILITY = "transport/carpark-availability"
private const val QUERY_DATA_TIME = "data_time"

interface CarparkAvailabilityApi {

    @GET(CARPARK_AVAILABILITY)
    suspend fun getCarparkAvailability(
        @Query(value = QUERY_DATA_TIME) dateTime: String? = null
    ): CarparkAvailabilityDto
}