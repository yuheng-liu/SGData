package com.liuyuheng.sgdata.data.network

import com.liuyuheng.sgdata.data.network.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

private const val FOUR_DAY_OUTLOOK = "four-day-outlook"

interface WeatherForecastApi {
    @GET(FOUR_DAY_OUTLOOK)
    suspend fun getWeatherForecast(
        @Query(value = "date") date: String? = null,
    ): WeatherResponse
}
