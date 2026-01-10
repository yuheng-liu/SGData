package com.liuyuheng.sgdata.presentation.weatherforecast.model

import com.liuyuheng.sgdata.domain.model.WeatherForecast

fun WeatherForecast.toUi(): WeatherForecastUi {
    return WeatherForecastUi(
        forecastsList = forecastsList.map { forecast ->
            WeatherForecastUi.ForecastUi(
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

private fun getTemperatureString(temperature: WeatherForecast.Forecast.Temperature): String {
    val unit = if (temperature.unit == "Degrees Celsius") "°C" else "°F"
    return "${temperature.low}$unit/${temperature.high}$unit"
}

private fun getRelativeHumidityString(relativeHumidity: WeatherForecast.Forecast.RelativeHumidity): String {
    val unit = if (relativeHumidity.unit == "Percentage") "%" else ""
    return "${relativeHumidity.low}$unit/${relativeHumidity.high}$unit"
}

private fun getWindString(wind: WeatherForecast.Forecast.Wind): String {
    return "${wind.speed.low}/${wind.speed.high} ${wind.direction}"
}

private fun getDetailsString(details: WeatherForecast.Forecast.ForecastDetails): String {
    return "(${details.code}) ${details.text.displayString}, ${details.summary}"
}