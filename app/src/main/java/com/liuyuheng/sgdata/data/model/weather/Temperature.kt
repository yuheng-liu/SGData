package com.liuyuheng.sgdata.data.model.weather

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Temperature(
    val low: Int,
    val high: Int,
    val unit: String,
)