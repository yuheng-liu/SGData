package com.liuyuheng.sgdata.weatherforecast.data.mapper

import com.liuyuheng.sgdata.core.utils.toLocalDateTimeOrNull
import com.liuyuheng.sgdata.weatherforecast.data.model.TwoHourForecastDto
import com.liuyuheng.sgdata.weatherforecast.domain.model.TwoHourForecast
import com.liuyuheng.sgdata.weatherforecast.domain.model.weather.TimePeriod
import com.liuyuheng.sgdata.weatherforecast.domain.model.weather.WeatherText

fun TwoHourForecastDto.toDomain(): TwoHourForecast {
    val latestItem = items.maxBy { it.updatedTimestamp }
    return TwoHourForecast(
        startTime = latestItem.timestamp.toLocalDateTimeOrNull(),
        updatedTimestamp = latestItem.updatedTimestamp.toLocalDateTimeOrNull(),
        timePeriod = TimePeriod(
            start = latestItem.validPeriod.start,
            end = latestItem.validPeriod.end,
            text = latestItem.validPeriod.text
        ),
        areaForecasts = latestItem.forecasts.map {
            TwoHourForecast.AreaForecast(
                area = it.area,
                forecast = WeatherText.valueOf(it.forecast.name)
            )
        }
    )
}