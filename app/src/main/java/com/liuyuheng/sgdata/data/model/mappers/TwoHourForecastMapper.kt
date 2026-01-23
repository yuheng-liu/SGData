package com.liuyuheng.sgdata.data.model.mappers

import com.liuyuheng.sgdata.data.model.TwoHourForecastDto
import com.liuyuheng.sgdata.domain.model.TwoHourForecast
import com.liuyuheng.sgdata.domain.model.weather.TimePeriod
import com.liuyuheng.sgdata.utils.toLocalDateTimeOrNull

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
                forecast = it.forecast
            )
        }
    )
}