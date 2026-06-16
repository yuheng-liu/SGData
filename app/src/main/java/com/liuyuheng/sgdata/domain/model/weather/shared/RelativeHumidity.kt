package com.liuyuheng.sgdata.domain.model.weather.shared

data class RelativeHumidity(
    val low: Int = 0,
    val high: Int = 0,
    val unit: String = "",
)