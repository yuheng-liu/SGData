package com.liuyuheng.sgdata.presentation.weatherforecast.twentyfourhour

import com.liuyuheng.sgdata.domain.model.weather.shared.ForecastDetails
import com.liuyuheng.sgdata.domain.model.weather.shared.RelativeHumidity
import com.liuyuheng.sgdata.domain.model.weather.shared.Temperature
import com.liuyuheng.sgdata.domain.model.weather.shared.TimePeriod
import com.liuyuheng.sgdata.domain.model.weather.shared.WeatherText
import com.liuyuheng.sgdata.domain.model.weather.shared.Wind
import java.time.LocalDateTime

data class TwentyFourHourForecastUi(
    val dateTime: LocalDateTime? = null,
    val temperature: Temperature = Temperature(),
    val relativeHumidity: RelativeHumidity = RelativeHumidity(),
    val wind: Wind = Wind(),
    val details: ForecastDetails = ForecastDetails(),
    val validPeriod: TimePeriod = TimePeriod(),
    val periodRegionForecasts: List<PeriodRegionForecastUi> = emptyList(),
) {
    data class PeriodRegionForecastUi(
        val timePeriod: String = "",
        val regions: RegionsUi = RegionsUi()
    ) {
        data class RegionsUi(
            val west: WeatherText = WeatherText.FAIR,
            val east: WeatherText = WeatherText.FAIR,
            val central: WeatherText = WeatherText.FAIR,
            val south: WeatherText = WeatherText.FAIR,
            val north: WeatherText = WeatherText.FAIR
        )
    }
}