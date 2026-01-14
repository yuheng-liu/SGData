package com.liuyuheng.sgdata.data.model.weather

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RelativeHumidity(
    val low: Int,
    val high: Int,
    val unit: String,
)