package com.liuyuheng.sgdata.presentation.weatherforecast.model

import java.time.LocalTime

data class WeatherForecastUi(
    val dataTimestamp: LocalTime? = null,
    val forecastsList: List<ForecastUi> = emptyList(),
) {
    data class ForecastUi(
        val date: String,
        val dayOfWeek: String,
        val temperature: String,
        val relativeHumidity: String,
        val wind: String,
        val details: String,
    )
}
