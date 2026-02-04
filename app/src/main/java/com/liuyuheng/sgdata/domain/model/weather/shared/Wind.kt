package com.liuyuheng.sgdata.domain.model.weather.shared

data class Wind(
    val speed: WindSpeed,
    val direction: String,
) {
    data class WindSpeed(
        val low: Int,
        val high: Int,
    )
}