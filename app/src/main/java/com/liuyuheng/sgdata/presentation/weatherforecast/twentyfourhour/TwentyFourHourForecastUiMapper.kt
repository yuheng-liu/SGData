package com.liuyuheng.sgdata.presentation.weatherforecast.twentyfourhour

import com.liuyuheng.sgdata.domain.model.weather.TwentyFourHourForecast
import com.liuyuheng.sgdata.domain.model.weather.shared.Temperature
import com.liuyuheng.sgdata.presentation.weatherforecast.getRelativeHumidityString
import com.liuyuheng.sgdata.presentation.weatherforecast.getWindString
import com.liuyuheng.sgdata.presentation.weatherforecast.temperatureUnitToSymbol

fun TwentyFourHourForecast.toUi(): TwentyFourHourForecastUi {
    return TwentyFourHourForecastUi(
        dateTime = updatedTimestamp,
        temperature = Temperature(
            low = generalForecast?.temperature?.low ?: 0,
            high = generalForecast?.temperature?.high ?: 0,
            unit = temperatureUnitToSymbol(generalForecast?.temperature?.unit)
        ),
        relativeHumidity = getRelativeHumidityString(generalForecast?.relativeHumidity),
        wind = getWindString(generalForecast?.wind),
        details = generalForecast?.forecast?.text?.displayString ?: "",
        validPeriod = generalForecast?.validTimePeriod?.text ?: "",
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