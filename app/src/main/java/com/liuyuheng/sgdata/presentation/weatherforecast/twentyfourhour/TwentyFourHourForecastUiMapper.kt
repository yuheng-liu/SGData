package com.liuyuheng.sgdata.presentation.weatherforecast.twentyfourhour

import com.liuyuheng.sgdata.domain.model.weather.TwentyFourHourForecast
import com.liuyuheng.sgdata.domain.model.weather.shared.ForecastDetails
import com.liuyuheng.sgdata.domain.model.weather.shared.RelativeHumidity
import com.liuyuheng.sgdata.domain.model.weather.shared.Temperature
import com.liuyuheng.sgdata.domain.model.weather.shared.TimePeriod
import com.liuyuheng.sgdata.domain.model.weather.shared.Wind
import com.liuyuheng.sgdata.domain.shared.temperatureUnitToSymbol

fun TwentyFourHourForecast.toUi(): TwentyFourHourForecastUi {
    return TwentyFourHourForecastUi(
        dateTime = updatedTimestamp,
        temperature = Temperature(
            low = generalForecast?.temperature?.low ?: 0,
            high = generalForecast?.temperature?.high ?: 0,
            unit = temperatureUnitToSymbol(generalForecast?.temperature?.unit)
        ),
        relativeHumidity = generalForecast?.relativeHumidity ?: RelativeHumidity(),
        wind = generalForecast?.wind ?: Wind(),
        details = generalForecast?.forecast ?: ForecastDetails(),
        validPeriod = generalForecast?.validTimePeriod ?: TimePeriod(),
        periodRegionForecasts = periodRegionForecasts.map { periodRegionForecast ->
            TwentyFourHourForecastUi.PeriodRegionForecastUi(
                timePeriod = periodRegionForecast.timePeriod.text,
                regions = TwentyFourHourForecastUi.PeriodRegionForecastUi.RegionsUi(
                    west = periodRegionForecast.regions.west.text,
                    east = periodRegionForecast.regions.east.text,
                    central = periodRegionForecast.regions.central.text,
                    south = periodRegionForecast.regions.south.text,
                    north = periodRegionForecast.regions.north.text
                )
            )
        }
    )
}