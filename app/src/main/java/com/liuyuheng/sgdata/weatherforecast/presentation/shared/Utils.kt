package com.liuyuheng.sgdata.weatherforecast.presentation.shared

import com.liuyuheng.sgdata.weatherforecast.domain.model.FourDayForecast
import com.liuyuheng.sgdata.weatherforecast.domain.model.weather.RelativeHumidity
import com.liuyuheng.sgdata.weatherforecast.domain.model.weather.Temperature
import com.liuyuheng.sgdata.weatherforecast.domain.model.weather.Wind

const val UNIT_DEGREE_CELSIUS = "°C"

fun getTemperatureString(temperature: Temperature?): String {
    return temperature?.let { temperature ->
        val unit = if (temperature.unit == "Degrees Celsius") "°C" else "°F"
        "${temperature.low}$unit/${temperature.high}$unit"
    } ?: ""
}

fun getRelativeHumidityString(relativeHumidity: RelativeHumidity?): String {
    return relativeHumidity?.let { relativeHumidity ->
        val unit = if (relativeHumidity.unit == "Percentage") "%" else ""
        "${relativeHumidity.low}$unit - ${relativeHumidity.high}$unit"
    } ?: ""
}

fun getWindString(wind: Wind?): String {
    return wind?.let { wind ->
        "${wind.direction}, (${wind.speed.low} - ${wind.speed.high}) km/h"
    } ?: ""
}

fun getDetailsString(details: FourDayForecast.Forecast.ForecastDetails?): String {
    return details?.let { details ->
        "(${details.code}) ${details.text.displayString}, ${details.summary}"
    } ?: ""
}