package com.liuyuheng.sgdata.weatherforecast.presentation.twentyfourhour

import com.liuyuheng.sgdata.core.utils.temperatureUnitToSymbol
import com.liuyuheng.sgdata.weatherforecast.domain.model.TwentyFourHourForecast
import com.liuyuheng.sgdata.weatherforecast.domain.model.weather.ForecastDetails
import com.liuyuheng.sgdata.weatherforecast.domain.model.weather.RelativeHumidity
import com.liuyuheng.sgdata.weatherforecast.domain.model.weather.Temperature
import com.liuyuheng.sgdata.weatherforecast.domain.model.weather.TimePeriod
import com.liuyuheng.sgdata.weatherforecast.domain.model.weather.Wind

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