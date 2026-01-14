package com.liuyuheng.sgdata.data.model

import com.liuyuheng.sgdata.data.model.weather.Day
import com.liuyuheng.sgdata.data.model.weather.RelativeHumidity
import com.liuyuheng.sgdata.data.model.weather.Temperature
import com.liuyuheng.sgdata.data.model.weather.WeatherText
import com.liuyuheng.sgdata.data.model.weather.Wind
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FourDayForecastDto(
    val records: List<WeatherRecord>,
) {
    @JsonClass(generateAdapter = true)
    data class WeatherRecord(
        val date: String, // Date of record
        val updatedTimestamp: String, // 2026-01-03T05:40:50+08:00
        val timestamp: String, // 2026-01-03T05:30:00+08:00
        val forecasts: List<Forecast>, // Forecast summary from date
    ) {
        @JsonClass(generateAdapter = true)
        data class Forecast(
            val timestamp: String, // Date of forecast, 2026-01-04T00:00:00+08:00
            val temperature: Temperature, // Unit of measure - Degrees Celsius
            val relativeHumidity: RelativeHumidity, // Unit of measure - Percentage
            val forecast: ForecastDetails,
            val day: Day,
            val wind: Wind,
        ) {

            @JsonClass(generateAdapter = true)
            data class ForecastDetails(
                val summary: String,
                val code: String,
                val text: WeatherText,
            )
        }
    }
}