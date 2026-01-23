package com.liuyuheng.sgdata.domain.model

import com.liuyuheng.sgdata.domain.model.weather.TimePeriod
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