package com.liuyuheng.sgdata.weatherforecast.domain.model.weather

data class ForecastDetails(
    val code: String = "",
    val text: WeatherText = WeatherText.UNKNOWN,
)