package com.liuyuheng.sgdata.domain.model

import com.liuyuheng.sgdata.domain.model.weather.ForecastDetails
import com.liuyuheng.sgdata.domain.model.weather.RelativeHumidity
import com.liuyuheng.sgdata.domain.model.weather.Temperature
import com.liuyuheng.sgdata.domain.model.weather.TimePeriod
import com.liuyuheng.sgdata.domain.model.weather.Wind
import java.time.LocalDate
import java.time.LocalDateTime

data class TwentyFourHourForecast(
    val date: LocalDate? = null,
    val updatedTimestamp: LocalDateTime? = null,
    val generalForecast: GeneralForecast? = null,
    val periodRegionForecasts: List<PeriodRegionForecast> = emptyList(),
) {
    data class GeneralForecast(
        val temperature: Temperature,
        val relativeHumidity: RelativeHumidity,
        val wind: Wind,
        val forecast: ForecastDetails,
        val validTimePeriod: TimePeriod,
    )

    data class PeriodRegionForecast(
        val timePeriod: TimePeriod,
        val regions: Regions
    ) {
        data class Regions(
            val west: ForecastDetails,
            val east: ForecastDetails,
            val central: ForecastDetails,
            val south: ForecastDetails,
            val north: ForecastDetails
        )
    }
}