package com.liuyuheng.sgdata.data.model

import com.liuyuheng.sgdata.data.model.weather.ValidPeriod
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TwoHourForecastDto(
    @Json(name = "area_metadata") val areaMetadata: List<AreaMetadata>,
    val items: List<TwoHourForecastItem>,
) {
    @JsonClass(generateAdapter = true)
    data class AreaMetadata(
        val name: String,
        @Json(name = "label_location") val coordinates: Coordinates,
    ) {
        @JsonClass(generateAdapter = true)
        data class Coordinates(
            val latitude: Double,
            val longitude: Double
        )
    }

    @JsonClass(generateAdapter = true)
    data class TwoHourForecastItem(
        @Json(name = "update_timestamp") val updatedTimestamp: String,
        val timestamp: String,
        @Json(name = "valid_period") val validPeriod: ValidPeriod,
        val forecasts: List<AreaForecast>
    ) {
        @JsonClass(generateAdapter = true)
        data class AreaForecast(
            val area: String,
            val forecast: String,
        )
    }
}