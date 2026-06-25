package com.liuyuheng.sgdata.weatherforecast.presentation.twentyfourhour

import com.liuyuheng.sgdata.weatherforecast.domain.model.TwentyFourHourForecast
import com.liuyuheng.sgdata.weatherforecast.domain.model.TwentyFourHourForecast.GeneralForecast
import com.liuyuheng.sgdata.weatherforecast.domain.model.TwentyFourHourForecast.PeriodRegionForecast
import com.liuyuheng.sgdata.weatherforecast.domain.model.weather.ForecastDetails
import com.liuyuheng.sgdata.weatherforecast.domain.model.weather.RelativeHumidity
import com.liuyuheng.sgdata.weatherforecast.domain.model.weather.Temperature
import com.liuyuheng.sgdata.weatherforecast.domain.model.weather.TimePeriod
import com.liuyuheng.sgdata.weatherforecast.domain.model.weather.WeatherText
import com.liuyuheng.sgdata.weatherforecast.domain.model.weather.Wind
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDateTime

class TwentyFourHourForecastUiMapperTest {

    @Test
    fun `toUi maps fully populated TwentyFourHourForecast to TwentyFourHourForecastUi`() {
        val testDateTime = LocalDateTime.of(2026, 6, 25, 12, 0)

        val forecast = TwentyFourHourForecast(
            date = null,
            updatedTimestamp = testDateTime,
            generalForecast = GeneralForecast(
                temperature = Temperature(low = 24, high = 32, unit = "Degrees Celsius"),
                relativeHumidity = RelativeHumidity(low = 60, high = 90, unit = "Percentage"),
                wind = Wind(
                    speed = Wind.WindSpeed(low = 10, high = 20),
                    direction = "South"
                ),
                forecast = ForecastDetails(code = "FA", text = WeatherText.FAIR),
                validTimePeriod = TimePeriod(text = "24 Hours")
            ),
            periodRegionForecasts = listOf(
                PeriodRegionForecast(
                    timePeriod = TimePeriod(text = "Morning"),
                    regions = PeriodRegionForecast.Regions(
                        west = ForecastDetails(code = "W", text = WeatherText.CLOUDY),
                        east = ForecastDetails(code = "E", text = WeatherText.FAIR),
                        central = ForecastDetails(code = "C", text = WeatherText.LIGHT_RAIN),
                        south = ForecastDetails(code = "S", text = WeatherText.HEAVY_THUNDERY_SHOWERS),
                        north = ForecastDetails(code = "N", text = WeatherText.SHOWERS)
                    )
                )
            )
        )

        val uiModel = forecast.toUi()

        assertEquals(testDateTime, uiModel.dateTime)
        assertEquals(24, uiModel.temperature.low)
        assertEquals(32, uiModel.temperature.high)
        assertEquals("°C", uiModel.temperature.unit)
        assertEquals(60, uiModel.relativeHumidity.low)
        assertEquals(90, uiModel.relativeHumidity.high)
        assertEquals("Percentage", uiModel.relativeHumidity.unit)
        assertEquals("South", uiModel.wind.direction)
        assertEquals(10, uiModel.wind.speed.low)
        assertEquals(20, uiModel.wind.speed.high)
        assertEquals(WeatherText.FAIR, uiModel.details.text)
        assertEquals("24 Hours", uiModel.validPeriod.text)

        assertEquals(1, uiModel.periodRegionForecasts.size)
        val periodForecast = uiModel.periodRegionForecasts[0]
        assertEquals("Morning", periodForecast.timePeriod)
        assertEquals(WeatherText.CLOUDY, periodForecast.regions.west)
        assertEquals(WeatherText.FAIR, periodForecast.regions.east)
        assertEquals(WeatherText.LIGHT_RAIN, periodForecast.regions.central)
        assertEquals(WeatherText.HEAVY_THUNDERY_SHOWERS, periodForecast.regions.south)
        assertEquals(WeatherText.SHOWERS, periodForecast.regions.north)
    }

    @Test
    fun `toUi falls back to default empty values when generalForecast is null`() {
        val forecast = TwentyFourHourForecast(
            date = null,
            updatedTimestamp = null,
            generalForecast = null,
            periodRegionForecasts = emptyList()
        )

        val uiModel = forecast.toUi()

        assertEquals(Temperature(), uiModel.temperature)
        assertEquals(RelativeHumidity(), uiModel.relativeHumidity)
        assertEquals(Wind(), uiModel.wind)
        assertEquals(ForecastDetails(), uiModel.details)
        assertEquals(TimePeriod(), uiModel.validPeriod)
        assertEquals(0, uiModel.periodRegionForecasts.size)
    }
}
