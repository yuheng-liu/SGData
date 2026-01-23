package com.liuyuheng.sgdata.data.model.weather

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ValidPeriod(
    val start: String,
    val end: String,
    val text: String
)