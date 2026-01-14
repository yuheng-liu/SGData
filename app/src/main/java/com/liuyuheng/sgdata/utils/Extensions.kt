package com.liuyuheng.sgdata.utils

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.OffsetDateTime
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

fun Long.toLocalDateOrNull(
    zoneId: ZoneId = ZoneId.systemDefault(),
): LocalDate {
    return Instant.ofEpochMilli(this)
        .atZone(zoneId)
        .toLocalDate()
}

fun String.toLocalDateOrNull(): LocalDate? {
    return try {
        LocalDate.parse(this)
    } catch (_: Exception) {
        null
    }
}

fun String.toLocalDateTimeOrNull(): LocalDateTime? {
    return try {
        OffsetDateTime.parse(this).toLocalDateTime()
    } catch (_: Exception) {
        null
    }
}