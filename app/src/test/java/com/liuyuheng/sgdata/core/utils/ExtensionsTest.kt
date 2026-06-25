package com.liuyuheng.sgdata.core.utils

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId

class ExtensionsTest {

    @Test
    fun `Long toFormattedDate formats epoch milliseconds correctly`() {
        val zoneId = ZoneId.of("UTC")
        val expectedDate = LocalDate.of(2026, 6, 25)
        val millis = expectedDate.toEpochMillis(zoneId)
        val result = millis.toFormattedDate("yyyy-MM-dd HH:mm:ss", zoneId)
        assertEquals("2026-06-25 00:00:00", result)
    }

    @Test
    fun `Long toLocalDate converts epoch milliseconds correctly`() {
        val zoneId = ZoneId.of("UTC")
        val expectedDate = LocalDate.of(2026, 6, 25)
        val millis = expectedDate.toEpochMillis(zoneId)
        val result = millis.toLocalDate(zoneId)
        assertEquals(expectedDate, result)
    }

    @Test
    fun `String toLocalDateOrNull parses valid and invalid dates`() {
        assertEquals(LocalDate.of(2026, 6, 25), "2026-06-25".toLocalDateOrNull())
        assertNull("invalid-date".toLocalDateOrNull())
        assertNull("".toLocalDateOrNull())
    }

    @Test
    fun `String toLocalDateTimeOrNull parses offset date times`() {
        val dateTimeString = "2026-06-25T12:00:00+08:00"
        val expected = LocalDateTime.of(2026, 6, 25, 12, 0)
        assertEquals(expected, dateTimeString.toLocalDateTimeOrNull())

        assertNull("invalid-datetime".toLocalDateTimeOrNull())
    }

    @Test
    fun `Boolean toYesNo returns Yes or No`() {
        assertEquals("Yes", true.toYesNo())
        assertEquals("No", false.toYesNo())
    }

    @Test
    fun `String toCapitalize capitalizes single or all words`() {
        assertEquals("Hello", "hello".toCapitalize())
        assertEquals("Hello world", "hello world".toCapitalize(allWords = false))
        assertEquals("Hello World", "hello world".toCapitalize(allWords = true))
        assertEquals("Bukit Batok West", "bukit batok west".toCapitalize(allWords = true))
    }

    @Test
    fun `LocalDateTime toDisplayDate formats to standard format with ordinal suffix`() {
        val dateTime1 = LocalDateTime.of(2026, 6, 1, 10, 0)
        assertEquals("1st Jun 2026", dateTime1.toDisplayDate())

        val dateTime2 = LocalDateTime.of(2026, 6, 2, 10, 0)
        assertEquals("2nd Jun 2026", dateTime2.toDisplayDate())

        val dateTime3 = LocalDateTime.of(2026, 6, 3, 10, 0)
        assertEquals("3rd Jun 2026", dateTime3.toDisplayDate())

        val dateTime4 = LocalDateTime.of(2026, 6, 4, 10, 0)
        assertEquals("4th Jun 2026", dateTime4.toDisplayDate())

        val dateTime11 = LocalDateTime.of(2026, 6, 11, 10, 0)
        assertEquals("11th Jun 2026", dateTime11.toDisplayDate())

        val dateTime21 = LocalDateTime.of(2026, 6, 21, 10, 0)
        assertEquals("21st Jun 2026", dateTime21.toDisplayDate())
    }

    @Test
    fun `LocalDateTime toDisplayDateV2 formats correctly`() {
        val dateTime = LocalDateTime.of(2026, 6, 25, 10, 0)
        assertEquals("Thu 25th Jun", dateTime.toDisplayDateV2())
    }

    @Test
    fun `LocalDate toEpochMillis converts correctly`() {
        val localDate = LocalDate.of(2026, 6, 25)
        val epochMillis = localDate.toEpochMillis(ZoneId.of("UTC"))
        val expected = localDate.atStartOfDay(ZoneId.of("UTC")).toInstant().toEpochMilli()
        assertEquals(expected, epochMillis)
    }

    @Test
    fun `Int ordinalSuffix returns correct suffixes`() {
        assertEquals("st", 1.ordinalSuffix())
        assertEquals("nd", 2.ordinalSuffix())
        assertEquals("rd", 3.ordinalSuffix())
        assertEquals("th", 4.ordinalSuffix())
        assertEquals("th", 11.ordinalSuffix())
        assertEquals("th", 12.ordinalSuffix())
        assertEquals("th", 13.ordinalSuffix())
        assertEquals("st", 21.ordinalSuffix())
        assertEquals("nd", 22.ordinalSuffix())
        assertEquals("rd", 23.ordinalSuffix())
        assertEquals("th", 24.ordinalSuffix())
    }

    @Test
    fun `String toBooleanOrNull parses various boolean representation strings`() {
        assertTrue("yes".toBooleanOrNull() == true)
        assertTrue("YES".toBooleanOrNull() == true)
        assertTrue("y".toBooleanOrNull() == true)
        assertTrue("Y".toBooleanOrNull() == true)

        assertTrue("no".toBooleanOrNull() == false)
        assertTrue("NO".toBooleanOrNull() == false)
        assertTrue("n".toBooleanOrNull() == false)
        assertTrue("N".toBooleanOrNull() == false)

        assertNull("maybe".toBooleanOrNull())
        assertNull("".toBooleanOrNull())
        assertNull((null as String?).toBooleanOrNull())
    }
}
