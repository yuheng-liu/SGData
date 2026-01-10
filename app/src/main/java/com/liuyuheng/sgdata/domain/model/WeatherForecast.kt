package com.liuyuheng.sgdata.domain.model

import java.time.DayOfWeek
import java.time.LocalDate


data class WeatherForecast(
    val startDate: LocalDate? = null,
    val forecastsList: List<Forecast> = emptyList(),  // map of date to list of forecasts for that date
) {
    data class Forecast(
        val date: LocalDate,    // date of forecast
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
            enum class WeatherText(val displayString: String) {
                FAIR("Fair"),
                FAIR_DAY("Fair Day"),
                FAIR_NIGHT("Fair Night"),
                FAIR_AND_WARM("Fair And Warm"),
                PARTLY_CLOUDY("Partly Cloudy"),
                PARTLY_CLOUDY_DAY("Partly Cloudy Day"),
                PARTLY_CLOUDY_NIGHT("Partly Cloudy Night"),
                CLOUDY("Cloudy"),
                HAZY("Hazy"),
                SLIGHTLY_HAZY("Slightly Hazy"),
                WINDY("Windy"),
                MIST("Mist"),
                FOG("Fog"),
                LIGHT_RAIN("Light Rain"),
                MODERATE_RAIN("Moderate Rain"),
                HEAVY_RAIN("Heavy Rain"),
                PASSING_SHOWERS("Passing Showers"),
                LIGHT_SHOWERS("Light Showers"),
                SHOWERS("Showers"),
                HEAVY_SHOWERS("Heavy Showers"),
                THUNDERY_SHOWERS("Thundery Showers"),
                HEAVY_THUNDERY_SHOWERS("Heavy Thundery Showers"),
                HEAVY_THUNDERY_SHOWERS_WITH_GUSTY_WINDS("Heavy Thundery Showers With Gusty Winds"),
            }
        }
    }
}
