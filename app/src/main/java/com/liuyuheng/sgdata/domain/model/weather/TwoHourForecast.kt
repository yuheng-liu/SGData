package com.liuyuheng.sgdata.domain.model.weather

import com.liuyuheng.sgdata.domain.model.weather.shared.TimePeriod
import com.liuyuheng.sgdata.domain.model.weather.shared.WeatherText
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