package com.liuyuheng.sgdata.shared

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
    val formatter = DateTimeFormatter.ofPattern("MMM yyyy")
    return "$dayOfMonth${dayOfMonth.ordinalSuffix()} ${format(formatter)}"
}

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