package com.liuyuheng.sgdata.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherForecastDto(
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
            data class Temperature(
                val low: Int,
                val high: Int,
                val unit: String,
            )

            @JsonClass(generateAdapter = true)
            data class RelativeHumidity(
                val low: Int,
                val high: Int,
                val unit: String,
            )

            @JsonClass(generateAdapter = true)
            data class ForecastDetails(
                val summary: String,
                val code: String,
                val text: WeatherText,
            ) {
                @Suppress("unused")
                enum class WeatherText {
                    @Json(name = "Fair")
                    FAIR,

                    @Json(name = "Fair (Day)")
                    FAIR_DAY,

                    @Json(name = "Fair (Night)")
                    FAIR_NIGHT,

                    @Json(name = "Fair and Warm")
                    FAIR_AND_WARM,

                    @Json(name = "Partly Cloudy")
                    PARTLY_CLOUDY,

                    @Json(name = "Partly Cloudy (Day)")
                    PARTLY_CLOUDY_DAY,

                    @Json(name = "Partly Cloudy (Night)")
                    PARTLY_CLOUDY_NIGHT,

                    @Json(name = "Cloudy")
                    CLOUDY,

                    @Json(name = "Hazy")
                    HAZY,

                    @Json(name = "Slightly Hazy")
                    SLIGHTLY_HAZY,

                    @Json(name = "Windy")
                    WINDY,

                    @Json(name = "Mist")
                    MIST,

                    @Json(name = "Fog")
                    FOG,

                    @Json(name = "Light Rain")
                    LIGHT_RAIN,

                    @Json(name = "Moderate Rain")
                    MODERATE_RAIN,

                    @Json(name = "Heavy Rain")
                    HEAVY_RAIN,

                    @Json(name = "Passing Showers")
                    PASSING_SHOWERS,

                    @Json(name = "Light Showers")
                    LIGHT_SHOWERS,

                    @Json(name = "Showers")
                    SHOWERS,

                    @Json(name = "Heavy Showers")
                    HEAVY_SHOWERS,

                    @Json(name = "Thundery Showers")
                    THUNDERY_SHOWERS,

                    @Json(name = "Heavy Thundery Showers")
                    HEAVY_THUNDERY_SHOWERS,

                    @Json(name = "Heavy Thundery Showers with Gusty Winds")
                    HEAVY_THUNDERY_SHOWERS_WITH_GUSTY_WINDS,
                }
            }

            @Suppress("unused")
            enum class Day {
                @Json(name = "Monday")
                MONDAY,

                @Json(name = "Tuesday")
                TUESDAY,

                @Json(name = "Wednesday")
                WEDNESDAY,

                @Json(name = "Thursday")
                THURSDAY,

                @Json(name = "Friday")
                FRIDAY,

                @Json(name = "Saturday")
                SATURDAY,

                @Json(name = "Sunday")
                SUNDAY,
            }

            @JsonClass(generateAdapter = true)
            data class Wind(
                val speed: WindSpeed, // Unit of measure - Kilometeres per hour
                val direction: String,
            ) {
                @JsonClass(generateAdapter = true)
                data class WindSpeed(
                    val low: Int,
                    val high: Int,
                )
            }
        }
    }
}