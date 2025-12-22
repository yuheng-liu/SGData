package com.liuyuheng.sgdata.domain.model

import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate


data class WeatherForecast(
    val forecastsMap: Map<LocalDate, List<Forecast>> = emptyMap(),
) {
    data class Forecast(
        val dayOfWeek: DayOfWeek,
        val temperature: Temperature,
        val relativeHumidity: RelativeHumidity,
        val wind: Wind,
        val details: ForecastDetails,
    ) {
        data class Temperature(
            val low: Int,
            val high: Int,
            val unit: String,
        )

        data class RelativeHumidity(
            val low: Int,
            val high: Int,
            val unit: String,
        )

        data class Wind(
            val speed: WindSpeed,
            val direction: String,
        ) {
            data class WindSpeed(
                val low: Int,
                val high: Int,
            )
        }

        data class ForecastDetails(
            val summary: String,
            val code: String,
            val text: WeatherText,
        ) {
            enum class WeatherText {
                FAIR,
                FAIR_DAY,
                FAIR_NIGHT,
                FAIR_AND_WARM,
                PARTLY_CLOUDY,
                PARTLY_CLOUDY_DAY,
                PARTLY_CLOUDY_NIGHT,
                CLOUDY,
                HAZY,
                SLIGHTLY_HAZY,
                WINDY,
                MIST,
                FOG,
                LIGHT_RAIN,
                MODERATE_RAIN,
                HEAVY_RAIN,
                PASSING_SHOWERS,
                LIGHT_SHOWERS,
                SHOWERS,
                HEAVY_SHOWERS,
                THUNDERY_SHOWERS,
                HEAVY_THUNDERY_SHOWERS,
                HEAVY_THUNDERY_SHOWERS_WITH_GUSTY_WINDS,
            }
        }
    }
}
