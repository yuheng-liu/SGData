package com.liuyuheng.sgdata.data.model.mappers

import com.liuyuheng.sgdata.data.model.TwentyFourHourForecastDto
import com.liuyuheng.sgdata.domain.model.weather.TwentyFourHourForecast
import com.liuyuheng.sgdata.domain.model.weather.shared.ForecastDetails
import com.liuyuheng.sgdata.domain.model.weather.shared.RelativeHumidity
import com.liuyuheng.sgdata.domain.model.weather.shared.Temperature
import com.liuyuheng.sgdata.domain.model.weather.shared.TimePeriod
import com.liuyuheng.sgdata.domain.model.weather.shared.WeatherText
import com.liuyuheng.sgdata.domain.model.weather.shared.Wind
import com.liuyuheng.sgdata.shared.toLocalDateOrNull
import com.liuyuheng.sgdata.shared.toLocalDateTimeOrNull

fun TwentyFourHourForecastDto.toDomain(): TwentyFourHourForecast {
    val latestRecord = records.maxBy { it.updatedTimestamp }
    return TwentyFourHourForecast(
        date = latestRecord.date.toLocalDateOrNull(),
        updatedTimestamp = latestRecord.updatedTimestamp.toLocalDateTimeOrNull(),
        generalForecast = TwentyFourHourForecast.GeneralForecast(
            temperature = Temperature(
                low = latestRecord.general.temperature.low,
                high = latestRecord.general.temperature.high,
                unit = latestRecord.general.temperature.unit
            ),
            relativeHumidity = RelativeHumidity(
                low = latestRecord.general.relativeHumidity.low,
                high = latestRecord.general.relativeHumidity.high,
                unit = latestRecord.general.relativeHumidity.unit
            ),
            wind = Wind(
                speed = Wind.WindSpeed(
                    low = latestRecord.general.wind.speed.low,
                    high = latestRecord.general.wind.speed.high
                ),
                direction = latestRecord.general.wind.direction
            ),
            forecast = ForecastDetails(
                code = latestRecord.general.forecast.code,
                text = WeatherText.valueOf(
                    latestRecord.general.forecast.text.name
                )
            ),
            validTimePeriod = TimePeriod(
                start = latestRecord.general.validPeriod.start,
                end = latestRecord.general.validPeriod.end,
                text = latestRecord.general.validPeriod.text
            )
        ),
        periodRegionForecasts = latestRecord.periods.map { dtoPeriod ->
            TwentyFourHourForecast.PeriodRegionForecast(
                timePeriod = TimePeriod(
                    start = dtoPeriod.timePeriod.start,
                    end = dtoPeriod.timePeriod.end,
                    text = dtoPeriod.timePeriod.text
                ),
                regions = TwentyFourHourForecast.PeriodRegionForecast.Regions(
                    west = ForecastDetails(
                        code = dtoPeriod.regions.west.code,
                        text = WeatherText.valueOf(
                            dtoPeriod.regions.west.text.name
                        )
                    ),
                    east = ForecastDetails(
                        code = dtoPeriod.regions.east.code,
                        text = WeatherText.valueOf(
                            dtoPeriod.regions.east.text.name
                        )
                    ),
                    central = ForecastDetails(
                        code = dtoPeriod.regions.central.code,
                        text = WeatherText.valueOf(
                            dtoPeriod.regions.central.text.name
                        )
                    ),
                    south = ForecastDetails(
                        code = dtoPeriod.regions.south.code,
                        text = WeatherText.valueOf(
                            dtoPeriod.regions.south.text.name
                        )
                    ),
                    north = ForecastDetails(
                        code = dtoPeriod.regions.north.code,
                        text = WeatherText.valueOf(
                            dtoPeriod.regions.north.text.name
                        )
                    )
                )
            )
        }
    )
}