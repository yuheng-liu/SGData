package com.liuyuheng.sgdata.domain.model.weather

import com.liuyuheng.sgdata.domain.model.weather.shared.RelativeHumidity
import com.liuyuheng.sgdata.domain.model.weather.shared.Temperature
import com.liuyuheng.sgdata.domain.model.weather.shared.WeatherText
import com.liuyuheng.sgdata.domain.model.weather.shared.Wind
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime


data class FourDayForecast(
    val startDate: LocalDate? = null,
    val updatedTimestamp: LocalDateTime? = null,
    val forecastsList: List<Forecast> = emptyList(),  // map of date to list of forecasts for that date
) {
    data class Forecast(
        val date: LocalDate,    // date of forecast
        val dayOfWeek: DayOfWeek,
        val temperature: Temperature,
        val relativeHumidity: RelativeHumidity,
        val wind: Wind,
        val details: ForecastDetails,
    ) {

        data class ForecastDetails(
            val summary: String,
            val code: String,
            val text: WeatherText,
        )
    }
}
