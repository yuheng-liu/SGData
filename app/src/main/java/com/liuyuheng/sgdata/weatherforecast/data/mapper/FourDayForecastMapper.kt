package com.liuyuheng.sgdata.weatherforecast.data.mapper

import com.liuyuheng.sgdata.core.utils.temperatureUnitToSymbol
import com.liuyuheng.sgdata.core.utils.toLocalDateTimeOrNull
import com.liuyuheng.sgdata.weatherforecast.data.model.FourDayForecastDto
import com.liuyuheng.sgdata.weatherforecast.domain.model.FourDayForecast
import com.liuyuheng.sgdata.weatherforecast.domain.model.weather.RelativeHumidity
import com.liuyuheng.sgdata.weatherforecast.domain.model.weather.Temperature
import com.liuyuheng.sgdata.weatherforecast.domain.model.weather.WeatherText
import com.liuyuheng.sgdata.weatherforecast.domain.model.weather.Wind
import java.time.DayOfWeek
import java.time.Instant
import java.time.ZoneId

fun FourDayForecastDto.toDomain(): FourDayForecast {
    val latestRecord = records.maxBy { it.updatedTimestamp }
    return FourDayForecast(
        startDate = latestRecord.date.toLocalDateTimeOrNull(),
        updatedTimestamp = latestRecord.updatedTimestamp.toLocalDateTimeOrNull(),
        forecastsList = latestRecord.forecasts.map { dtoForecast ->
            FourDayForecast.Forecast(
                date = Instant.parse(dtoForecast.timestamp)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime(),
                dayOfWeek = DayOfWeek.valueOf(dtoForecast.day.name),
                temperature = Temperature(
                    low = dtoForecast.temperature.low,
                    high = dtoForecast.temperature.high,
                    unit = temperatureUnitToSymbol(dtoForecast.temperature.unit),
                ),
                relativeHumidity = RelativeHumidity(
                    low = dtoForecast.relativeHumidity.low,
                    high = dtoForecast.relativeHumidity.high,
                    unit = dtoForecast.relativeHumidity.unit,
                ),
                wind = Wind(
                    speed = Wind.WindSpeed(
                        low = dtoForecast.wind.speed.low,
                        high = dtoForecast.wind.speed.high,
                    ),
                    direction = dtoForecast.wind.direction,
                ),
                details = FourDayForecast.Forecast.ForecastDetails(
                    summary = dtoForecast.forecast.summary,
                    code = dtoForecast.forecast.code,
                    text = WeatherText.valueOf(
                        dtoForecast.forecast.text.name
                    ),
                ),
            )
        }
    )
}