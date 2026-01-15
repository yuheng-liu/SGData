package com.liuyuheng.sgdata.data.model.mappers

import com.liuyuheng.sgdata.data.model.FourDayForecastDto
import com.liuyuheng.sgdata.domain.model.FourDayForecast
import com.liuyuheng.sgdata.domain.model.weather.RelativeHumidity
import com.liuyuheng.sgdata.domain.model.weather.Temperature
import com.liuyuheng.sgdata.domain.model.weather.WeatherText
import com.liuyuheng.sgdata.domain.model.weather.Wind
import com.liuyuheng.sgdata.utils.toLocalDateOrNull
import com.liuyuheng.sgdata.utils.toLocalDateTimeOrNull
import java.time.DayOfWeek
import java.time.Instant
import java.time.ZoneId

fun FourDayForecastDto.toDomain(): FourDayForecast {
    val latestRecord = records.maxBy { it.updatedTimestamp }
    return FourDayForecast(
        startDate = latestRecord.date.toLocalDateOrNull(),
        updatedTimestamp = latestRecord.updatedTimestamp.toLocalDateTimeOrNull(),
        forecastsList = latestRecord.forecasts.map { dtoForecast ->
            FourDayForecast.Forecast(
                date = Instant.parse(dtoForecast.timestamp)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate(),
                dayOfWeek = DayOfWeek.valueOf(dtoForecast.day.name),
                temperature = Temperature(
                    low = dtoForecast.temperature.low,
                    high = dtoForecast.temperature.high,
                    unit = dtoForecast.temperature.unit,
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