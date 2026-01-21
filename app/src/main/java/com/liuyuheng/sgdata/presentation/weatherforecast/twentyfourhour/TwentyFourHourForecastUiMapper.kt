package com.liuyuheng.sgdata.presentation.weatherforecast.twentyfourhour

import com.liuyuheng.sgdata.domain.model.TwentyFourHourForecast
import com.liuyuheng.sgdata.presentation.shared.getRelativeHumidityString
import com.liuyuheng.sgdata.presentation.shared.getTemperatureString
import com.liuyuheng.sgdata.presentation.shared.getWindString

fun TwentyFourHourForecast.toUi(): TwentyFourHourForecastUi {
    return TwentyFourHourForecastUi(
        dateTime = updatedTimestamp,
        temperature = getTemperatureString(generalForecast?.temperature),
        relativeHumidity = getRelativeHumidityString(generalForecast?.relativeHumidity),
        wind = getWindString(generalForecast?.wind),
        details = generalForecast?.forecast?.text?.displayString ?: "",
        validPeriod = generalForecast?.validTimePeriod?.text ?: "",
        periodRegionForecasts = periodRegionForecasts.map { periodRegionForecast ->
            TwentyFourHourForecastUi.PeriodRegionForecastUi(
                timePeriod = periodRegionForecast.timePeriod.text,
                regions = TwentyFourHourForecastUi.PeriodRegionForecastUi.RegionsUi(
                    west = periodRegionForecast.regions.west.text.displayString,
                    east = periodRegionForecast.regions.east.text.displayString,
                    central = periodRegionForecast.regions.central.text.displayString,
                    south = periodRegionForecast.regions.south.text.displayString,
                    north = periodRegionForecast.regions.north.text.displayString
                )
            )
        }
    )
}