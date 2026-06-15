package com.liuyuheng.sgdata.shared

import java.time.LocalDate
import java.time.ZoneId

fun getTodayMillis(): Long = LocalDate.now()
    .atStartOfDay(ZoneId.systemDefault())
    .toInstant()
    .toEpochMilli()