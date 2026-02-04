package com.liuyuheng.sgdata.domain.model.weather

import com.liuyuheng.sgdata.domain.model.weather.shared.TimePeriod
import java.time.LocalDateTime

data class TwoHourForecast(
    val startTime: LocalDateTime? = null,
    val updatedTimestamp: LocalDateTime? = null,
    val timePeriod: TimePeriod = TimePeriod(),
    val areaForecasts: List<AreaForecast> = emptyList()
) {
    data class AreaForecast(
        val area: String,
        val forecast: String,
    )
}