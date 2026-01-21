package com.liuyuheng.sgdata.presentation.weatherforecast.twentyfourhour

import java.time.LocalDateTime

data class TwentyFourHourForecastUi(
    val dateTime: LocalDateTime? = null,
    val temperature: String = "",
    val relativeHumidity: String = "",
    val wind: String = "",
    val details: String = "",
    val validPeriod: String = "",
    val periodRegionForecasts: List<PeriodRegionForecastUi> = emptyList(),
) {
    data class PeriodRegionForecastUi(
        val timePeriod: String = "",
        val regions: RegionsUi = RegionsUi()
    ) {
        data class RegionsUi(
            val west: String = "",
            val east: String = "",
            val central: String = "",
            val south: String = "",
            val north: String = ""
        )
    }
}