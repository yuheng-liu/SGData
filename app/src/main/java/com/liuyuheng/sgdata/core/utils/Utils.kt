package com.liuyuheng.sgdata.core.utils

import java.time.LocalDate
import java.time.ZoneId

fun getTodayMillis(): Long = LocalDate.now()
    .atStartOfDay(ZoneId.systemDefault())
    .toInstant()
    .toEpochMilli()

fun String?.toBooleanOrNull(): Boolean? {
    return this?.let {
        when (it.trim().lowercase()) {
            "y", "yes" -> true
            "n", "no" -> false
            else -> null
        }
    }
}

fun temperatureUnitToSymbol(unit: String?): String {
    return when (unit) {
        "Degrees Celsius" -> "°C"
        "Degrees Fahrenheit" -> "°F"
        else -> ""
    }
}