package com.liuyuheng.sgdata.data.model

import com.liuyuheng.sgdata.domain.model.WeatherForecast
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate

fun WeatherForecastDto.toDomain(): WeatherForecast {
    return WeatherForecast(
        records.associate { record ->
            LocalDate.parse(record.date) to record.forecasts.map { dtoForecast ->
                WeatherForecast.Forecast(
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
            }
        }
    )
}