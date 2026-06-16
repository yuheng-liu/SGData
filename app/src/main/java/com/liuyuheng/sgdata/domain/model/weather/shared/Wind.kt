package com.liuyuheng.sgdata.domain.model.weather.shared

data class Wind(
    val speed: WindSpeed = WindSpeed(),
    val direction: String = "",
) {
    data class WindSpeed(
        val low: Int = 0,
        val high: Int = 0,
    )
}