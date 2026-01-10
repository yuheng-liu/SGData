package com.liuyuheng.sgdata.data.model

import com.liuyuheng.sgdata.domain.model.WeatherForecast
import com.liuyuheng.sgdata.utils.toLocalDate
import java.time.DayOfWeek
import java.time.Instant
import java.time.ZoneId

fun WeatherForecastDto.toDomain(): WeatherForecast {
    return WeatherForecast(
        startDate = records.firstOrNull()?.date?.toLocalDate(),
        forecastsList = records.maxByOrNull { it.updatedTimestamp }?.forecasts?.map { dtoForecast ->
            WeatherForecast.Forecast(
                date = Instant.parse(dtoForecast.timestamp)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate(),
                dayOfWeek = DayOfWeek.valueOf(dtoForecast.day.name),
                temperature = WeatherForecast.Forecast.Temperature(
                    low = dtoForecast.temperature.low,
                    high = dtoForecast.temperature.high,
                    unit = dtoForecast.temperature.unit,
                ),
                relativeHumidity = WeatherForecast.Forecast.RelativeHumidity(
                    low = dtoForecast.relativeHumidity.low,
                    high = dtoForecast.relativeHumidity.high,
                    unit = dtoForecast.relativeHumidity.unit,
                ),
                wind = WeatherForecast.Forecast.Wind(
                    speed = WeatherForecast.Forecast.Wind.WindSpeed(
                        low = dtoForecast.wind.speed.low,
                        high = dtoForecast.wind.speed.high,
                    ),
                    direction = dtoForecast.wind.direction,
                ),
                details = WeatherForecast.Forecast.ForecastDetails(
                    summary = dtoForecast.forecast.summary,
                    code = dtoForecast.forecast.code,
                    text = WeatherForecast.Forecast.ForecastDetails.WeatherText.valueOf(
                        dtoForecast.forecast.text.name
                    ),
                ),
            )
        } ?: emptyList()
    )
}