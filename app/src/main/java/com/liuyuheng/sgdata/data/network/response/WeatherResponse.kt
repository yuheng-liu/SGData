package com.liuyuheng.sgdata.data.network.response

import com.liuyuheng.sgdata.data.model.WeatherForecastDto
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherResponse(
    val code: Int, // Response status code (always 0 for success)
    val name: String = "", // example "ERROR_PARAMS", "REAL_TIME_API_DATA_NOT_FOUND"
    val errorMsg: String, // Error message (empty string for success)
    val data: WeatherForecastDto, // Chronologically ordered forecasts for the next 4 days
)