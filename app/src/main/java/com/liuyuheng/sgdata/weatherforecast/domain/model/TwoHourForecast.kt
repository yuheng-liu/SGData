package com.liuyuheng.sgdata.weatherforecast.domain.model

import com.liuyuheng.sgdata.weatherforecast.domain.model.weather.TimePeriod
import com.liuyuheng.sgdata.weatherforecast.domain.model.weather.WeatherText
import java.time.LocalDateTime

data class TwoHourForecast(
    val startTime: LocalDateTime? = null,
    val updatedTimestamp: LocalDateTime? = null,
    val timePeriod: TimePeriod = TimePeriod(),
    val areaForecasts: List<AreaForecast> = emptyList()
) {
    data class AreaForecast(
        val area: String,
        val forecast: WeatherText,
    )
}