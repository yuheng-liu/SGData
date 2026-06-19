package com.liuyuheng.sgdata.core.utils

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

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

fun Boolean.toYesNo(): String = if (this) "Yes" else "No"

fun String.toCapitalize(allWords: Boolean = false): String {
    return lowercase().run {
        if (allWords) {
            split(" ").joinToString(" ") { word ->
                word.replaceFirstChar { it.titlecase() }
            }
        } else {
            this.replaceFirstChar { it.titlecase() }
        }
    }
}

fun LocalDateTime.toDisplayDate(): String {
    val formatter = DateTimeFormatter.ofPattern("MMM yyyy", Locale.ENGLISH)
    return "$dayOfMonth${dayOfMonth.ordinalSuffix()} ${format(formatter)}"
}

fun LocalDateTime.toDisplayDateV2(): String {
    val formatter = DateTimeFormatter.ofPattern("EEE MMM", Locale.ENGLISH)
    val parts = format(formatter).split(" ")
    return "${parts[0]} $dayOfMonth${dayOfMonth.ordinalSuffix()} ${parts[1]}"
}

fun LocalDate.toEpochMillis(zoneId: ZoneId = ZoneId.systemDefault()): Long = atStartOfDay(zoneId)
    .toInstant()
    .toEpochMilli()


fun Int.ordinalSuffix(): String {
    return if (this in 11..13) {
        "th"
    } else {
        when (this % 10) {
            1 -> "st"
            2 -> "nd"
            3 -> "rd"
            else -> "th"
        }
    }
}

fun String?.toBooleanOrNull(): Boolean? {
    return this?.let {
        when (it.trim().lowercase()) {
            "y", "yes" -> true
            "n", "no" -> false
            else -> null
        }
    }
}