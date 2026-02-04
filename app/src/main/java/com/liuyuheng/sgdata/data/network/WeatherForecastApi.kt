package com.liuyuheng.sgdata.data.network

import com.liuyuheng.sgdata.data.model.FourDayForecastDto
import com.liuyuheng.sgdata.data.model.TwentyFourHourForecastDto
import com.liuyuheng.sgdata.data.model.TwoHourForecastDto
import com.liuyuheng.sgdata.data.network.response.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Query

private const val QUERY_DATE = "date"

interface WeatherForecastApi {
    @GET("four-day-outlook")
    suspend fun getFourDayForecast(
        @Query(value = QUERY_DATE) date: String? = null,
    ): BaseResponse<FourDayForecastDto>

    @GET("twenty-four-hr-forecast")
    suspend fun getTwentyFourHourForecast(
        @Query(value = QUERY_DATE) date: String? = null,
    ): BaseResponse<TwentyFourHourForecastDto>

    @GET("two-hr-forecast")
    suspend fun getTwoHourForecast(
        @Query(value = QUERY_DATE) date: String? = null,
    ): BaseResponse<TwoHourForecastDto>
}
