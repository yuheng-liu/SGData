package com.liuyuheng.sgdata.data.network

import com.liuyuheng.sgdata.data.model.FourDayForecastDto
import com.liuyuheng.sgdata.data.model.TwentyFourHourForecastDto
import com.liuyuheng.sgdata.data.network.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

private const val FOUR_DAY_OUTLOOK = "four-day-outlook"
private const val TWENTY_FOUR_HOUR_FORECAST = "twenty-four-hr-forecast"

interface WeatherForecastApi {
    @GET(FOUR_DAY_OUTLOOK)
    suspend fun getFourDayForecast(
        @Query(value = "date") date: String? = null,
    ): WeatherResponse<FourDayForecastDto>

    @GET(TWENTY_FOUR_HOUR_FORECAST)
    suspend fun getTwentyFourHourForecast(
        @Query(value = "date") date: String? = null,
    ): WeatherResponse<TwentyFourHourForecastDto>
}
