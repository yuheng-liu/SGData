package com.liuyuheng.sgdata.data.model.weather

import com.squareup.moshi.Json

@Suppress("unused")
enum class Day {
    @Json(name = "Monday")
    MONDAY,

    @Json(name = "Tuesday")
    TUESDAY,

    @Json(name = "Wednesday")
    WEDNESDAY,

    @Json(name = "Thursday")
    THURSDAY,

    @Json(name = "Friday")
    FRIDAY,

    @Json(name = "Saturday")
    SATURDAY,

    @Json(name = "Sunday")
    SUNDAY,
}