package com.liuyuheng.sgdata.data.model

import com.liuyuheng.sgdata.data.model.weather.RelativeHumidity
import com.liuyuheng.sgdata.data.model.weather.Temperature
import com.liuyuheng.sgdata.data.model.weather.ValidPeriod
import com.liuyuheng.sgdata.data.model.weather.WeatherText
import com.liuyuheng.sgdata.data.model.weather.Wind
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TwentyFourHourForecastDto(
    val records: List<TwentyFourHourForecastRecord>
) {
    @JsonClass(generateAdapter = true)
    data class TwentyFourHourForecastRecord(
        val date: String,
        val updatedTimestamp: String,
        val timestamp: String,
        val general: General,
        val periods: List<Period>
    ) {
        @JsonClass(generateAdapter = true)
        data class General(
            val temperature: Temperature,
            val relativeHumidity: RelativeHumidity,
            val wind: Wind,
            val forecast: Forecast,
            val validPeriod: ValidPeriod
        ) {
            @JsonClass(generateAdapter = true)
            data class Forecast(
                val code: String,
                val text: WeatherText
            )
        }

        @JsonClass(generateAdapter = true)
        data class Period(
            val timePeriod: TimePeriod,
            val regions: Regions
        ) {
            @JsonClass(generateAdapter = true)
            data class TimePeriod(
                val start: String,
                val end: String,
                val text: String
            )

            @JsonClass(generateAdapter = true)
            data class Regions(
                val west: RegionForecast,
                val east: RegionForecast,
                val central: RegionForecast,
                val south: RegionForecast,
                val north: RegionForecast
            ) {
                @JsonClass(generateAdapter = true)
                data class RegionForecast(
                    val code: String,
                    val text: WeatherText
                )
            }
        }
    }
}