package com.liuyuheng.sgdata.utils

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun Long.toFormattedDate(
    pattern: String = "dd/MM/yyyy",
    zoneId: ZoneId = ZoneId.systemDefault(),
): String {
    return Instant.ofEpochMilli(this)
        .atZone(zoneId)
        .format(DateTimeFormatter.ofPattern(pattern))
}

fun Long.toLocalDate(
    zoneId: ZoneId = ZoneId.systemDefault(),
): LocalDate {
    return Instant.ofEpochMilli(this)
        .atZone(zoneId)
        .toLocalDate()
}

fun String.toLocalDate(): LocalDate {
    return LocalDate.parse(this)
}