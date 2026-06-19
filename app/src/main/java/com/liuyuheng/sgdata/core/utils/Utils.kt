package com.liuyuheng.sgdata.core.utils

import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId

fun getTodayMillis(): Long = LocalDate.now()
    .atStartOfDay(ZoneId.systemDefault())
    .toInstant()
    .toEpochMilli()

fun temperatureUnitToSymbol(unit: String?): String {
    return when (unit) {
        "Degrees Celsius" -> "°C"
        "Degrees Fahrenheit" -> "°F"
        else -> ""
    }
}

fun hasTimePassedSince(prevTime: LocalDateTime, threshold: Duration) = Duration.between(prevTime, LocalDateTime.now()) >= threshold