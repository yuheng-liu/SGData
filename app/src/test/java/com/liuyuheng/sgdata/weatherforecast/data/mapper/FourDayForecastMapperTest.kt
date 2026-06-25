package com.liuyuheng.sgdata.weatherforecast.data.mapper

import com.liuyuheng.sgdata.weatherforecast.data.model.FourDayForecastDto
import com.liuyuheng.sgdata.weatherforecast.data.model.FourDayForecastDto.FourDayForecastRecord
import com.liuyuheng.sgdata.weatherforecast.data.model.FourDayForecastDto.FourDayForecastRecord.Forecast
import com.liuyuheng.sgdata.weatherforecast.data.model.FourDayForecastDto.FourDayForecastRecord.Forecast.ForecastDetails
import com.liuyuheng.sgdata.weatherforecast.data.model.weather.Day
import com.liuyuheng.sgdata.weatherforecast.data.model.weather.RelativeHumidity
import com.liuyuheng.sgdata.weatherforecast.data.model.weather.Temperature
import com.liuyuheng.sgdata.weatherforecast.data.model.weather.WeatherText
import com.liuyuheng.sgdata.weatherforecast.data.model.weather.Wind
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class FourDayForecastMapperTest {

    @Test
    fun `toDomain maps FourDayForecastDto to domain model selecting the latest record`() {
        val olderRecord = FourDayForecastRecord(
            date = "2026-06-24T00:00:00+08:00",
            updatedTimestamp = "2026-06-24T12:00:00+08:00",
            timestamp = "2026-06-24T12:00:00+08:00",
            forecasts = emptyList()
        )

        val latestRecord = FourDayForecastRecord(
            date = "2026-06-25T00:00:00+08:00",
            updatedTimestamp = "2026-06-25T12:00:00+08:00",
            timestamp = "2026-06-25T12:00:00+08:00",
            forecasts = listOf(
                Forecast(
                    timestamp = "2026-06-26T00:00:00Z",
                    temperature = Temperature(low = 25, high = 31, unit = "Degrees Celsius"),
                    relativeHumidity = RelativeHumidity(low = 65, high = 95, unit = "Percentage"),
                    forecast = ForecastDetails(summary = "Thundery Showers", code = "TS", text = WeatherText.THUNDERY_SHOWERS),
                    day = Day.FRIDAY,
                    wind = Wind(
                        speed = Wind.WindSpeed(low = 10, high = 15),
                        direction = "South"
                    )
                )
            )
        )

        val dto = FourDayForecastDto(records = listOf(olderRecord, latestRecord))
        val domain = dto.toDomain()

        val expectedDate = LocalDateTime.of(2026, 6, 25, 0, 0)
        val expectedUpdated = LocalDateTime.of(2026, 6, 25, 12, 0)
        assertEquals(expectedDate, domain.startDate)
        assertEquals(expectedUpdated, domain.updatedTimestamp)

        assertEquals(1, domain.forecastsList.size)
        val forecast = domain.forecastsList[0]

        val expectedTimestamp = Instant.parse("2026-06-26T00:00:00Z")
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime()
        assertEquals(expectedTimestamp, forecast.date)
        assertEquals(DayOfWeek.FRIDAY, forecast.dayOfWeek)
        assertEquals(25, forecast.temperature.low)
        assertEquals(31, forecast.temperature.high)
        assertEquals("°C", forecast.temperature.unit)
        assertEquals(65, forecast.relativeHumidity.low)
        assertEquals(95, forecast.relativeHumidity.high)
        assertEquals("Percentage", forecast.relativeHumidity.unit)
        assertEquals(10, forecast.wind.speed.low)
        assertEquals(15, forecast.wind.speed.high)
        assertEquals("South", forecast.wind.direction)
        assertEquals("Thundery Showers", forecast.details.summary)
        assertEquals("TS", forecast.details.code)
        assertEquals(com.liuyuheng.sgdata.weatherforecast.domain.model.weather.WeatherText.THUNDERY_SHOWERS, forecast.details.text)
    }
}
