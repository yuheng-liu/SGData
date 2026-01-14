package com.liuyuheng.sgdata.data.model.weather

import com.squareup.moshi.JsonClass

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