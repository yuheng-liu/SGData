package com.liuyuheng.sgdata.presentation.weatherforecast.fourday

import com.liuyuheng.sgdata.domain.model.FourDayForecast
import com.liuyuheng.sgdata.presentation.shared.getDetailsString
import com.liuyuheng.sgdata.presentation.shared.getRelativeHumidityString
import com.liuyuheng.sgdata.presentation.shared.getTemperatureString
import com.liuyuheng.sgdata.presentation.shared.getWindString

fun FourDayForecast.toUi(): FourDayForecastUi {
    return FourDayForecastUi(
        dataTimestamp = updatedTimestamp?.toLocalTime(),
        forecastsList = forecastsList.map { forecast ->
            FourDayForecastUi.DayForecast(
                date = forecast.date.toString(),
                dayOfWeek = forecast.dayOfWeek.toString(),
                temperature = getTemperatureString(forecast.temperature),
                relativeHumidity = getRelativeHumidityString(forecast.relativeHumidity),
                wind = getWindString(forecast.wind),
                details = getDetailsString(forecast.details),
            )
        }
    )
}