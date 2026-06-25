package com.liuyuheng.sgdata.core.utils

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId

class UtilsTest {

    @Test
    fun `getTodayMillis returns epoch milliseconds for start of today`() {
        val expected = LocalDate.now()
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli()
        assertEquals(expected, getTodayMillis())
    }

    @Test
    fun `temperatureUnitToSymbol returns correct symbols`() {
        assertEquals("°C", temperatureUnitToSymbol("Degrees Celsius"))
        assertEquals("°F", temperatureUnitToSymbol("Degrees Fahrenheit"))
        assertEquals("", temperatureUnitToSymbol("Degrees Kelvin"))
        assertEquals("", temperatureUnitToSymbol(null))
    }

    @Test
    fun `hasTimePassedSince returns true if duration threshold has passed`() {
        val twoHoursAgo = LocalDateTime.now().minusHours(2)
        val threshold = Duration.ofHours(1)
        assertTrue(hasTimePassedSince(twoHoursAgo, threshold))
    }

    @Test
    fun `hasTimePassedSince returns false if duration threshold has not passed`() {
        val thirtyMinutesAgo = LocalDateTime.now().minusMinutes(30)
        val threshold = Duration.ofHours(1)
        assertFalse(hasTimePassedSince(thirtyMinutesAgo, threshold))
    }
}
