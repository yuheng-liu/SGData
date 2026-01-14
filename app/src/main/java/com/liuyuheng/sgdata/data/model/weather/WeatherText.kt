package com.liuyuheng.sgdata.data.model.weather

import com.squareup.moshi.Json

@Suppress("unused")
enum class WeatherText {
    @Json(name = "Fair")
    FAIR,

    @Json(name = "Fair (Day)")
    FAIR_DAY,

    @Json(name = "Fair (Night)")
    FAIR_NIGHT,

    @Json(name = "Fair and Warm")
    FAIR_AND_WARM,

    @Json(name = "Partly Cloudy")
    PARTLY_CLOUDY,

    @Json(name = "Partly Cloudy (Day)")
    PARTLY_CLOUDY_DAY,

    @Json(name = "Partly Cloudy (Night)")
    PARTLY_CLOUDY_NIGHT,

    @Json(name = "Cloudy")
    CLOUDY,

    @Json(name = "Hazy")
    HAZY,

    @Json(name = "Slightly Hazy")
    SLIGHTLY_HAZY,

    @Json(name = "Windy")
    WINDY,

    @Json(name = "Mist")
    MIST,

    @Json(name = "Fog")
    FOG,

    @Json(name = "Light Rain")
    LIGHT_RAIN,

    @Json(name = "Moderate Rain")
    MODERATE_RAIN,

    @Json(name = "Heavy Rain")
    HEAVY_RAIN,

    @Json(name = "Passing Showers")
    PASSING_SHOWERS,

    @Json(name = "Light Showers")
    LIGHT_SHOWERS,

    @Json(name = "Showers")
    SHOWERS,

    @Json(name = "Heavy Showers")
    HEAVY_SHOWERS,

    @Json(name = "Thundery Showers")
    THUNDERY_SHOWERS,

    @Json(name = "Heavy Thundery Showers")
    HEAVY_THUNDERY_SHOWERS,

    @Json(name = "Heavy Thundery Showers with Gusty Winds")
    HEAVY_THUNDERY_SHOWERS_WITH_GUSTY_WINDS,
}