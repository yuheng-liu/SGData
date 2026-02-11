package com.liuyuheng.sgdata.presentation.weatherforecast.twentyfourhour

import com.liuyuheng.sgdata.domain.model.weather.shared.Temperature
import com.liuyuheng.sgdata.domain.model.weather.shared.WeatherText
import java.time.LocalDateTime

data class TwentyFourHourForecastUi(
    val dateTime: LocalDateTime? = null,
    val temperature: Temperature = Temperature(),
    val relativeHumidity: String = "",
    val wind: String = "",
    val details: String = "",
    val validPeriod: String = "",
    val periodRegionForecasts: List<PeriodRegionForecastUi> = emptyList(),
) {
    data class PeriodRegionForecastUi(
        val timePeriod: String = "",
        val regions: RegionsUi
    ) {
        data class RegionsUi(
            val west: WeatherText,
            val east: WeatherText,
            val central: WeatherText,
            val south: WeatherText,
            val north: WeatherText
        )
    }
}