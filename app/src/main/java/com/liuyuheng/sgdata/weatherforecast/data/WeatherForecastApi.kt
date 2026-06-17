package com.liuyuheng.sgdata.weatherforecast.data

import com.liuyuheng.sgdata.core.data.network.responses.BaseResponse
import com.liuyuheng.sgdata.weatherforecast.data.model.FourDayForecastDto
import com.liuyuheng.sgdata.weatherforecast.data.model.TwentyFourHourForecastDto
import com.liuyuheng.sgdata.weatherforecast.data.model.TwoHourForecastDto
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
