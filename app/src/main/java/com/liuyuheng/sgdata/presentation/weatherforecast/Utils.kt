package com.liuyuheng.sgdata.presentation.weatherforecast

import com.liuyuheng.sgdata.domain.model.weather.FourDayForecast
import com.liuyuheng.sgdata.domain.model.weather.shared.RelativeHumidity
import com.liuyuheng.sgdata.domain.model.weather.shared.Temperature
import com.liuyuheng.sgdata.domain.model.weather.shared.Wind

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
        "${wind.direction} ${wind.speed.low} - ${wind.speed.high}"
    } ?: ""
}

fun getDetailsString(details: FourDayForecast.Forecast.ForecastDetails?): String {
    return details?.let { details ->
        "(${details.code}) ${details.text.displayString}, ${details.summary}"
    } ?: ""
}

fun temperatureUnitToSymbol(unit: String?): String {
    return when (unit) {
        "Degrees Celsius" -> "°C"
        "Degrees Fahrenheit" -> "°F"
        else -> ""
    }
}