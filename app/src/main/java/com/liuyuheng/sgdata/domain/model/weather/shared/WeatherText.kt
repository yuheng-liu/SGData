package com.liuyuheng.sgdata.domain.model.weather.shared

import com.liuyuheng.sgdata.R

enum class WeatherText(val displayString: String) {
    FAIR("Fair"),
    FAIR_DAY("Fair Day"),
    FAIR_NIGHT("Fair Night"),
    FAIR_AND_WARM("Fair And Warm"),
    PARTLY_CLOUDY("Partly Cloudy"),
    PARTLY_CLOUDY_DAY("Partly Cloudy Day"),
    PARTLY_CLOUDY_NIGHT("Partly Cloudy Night"),
    CLOUDY("Cloudy"),
    HAZY("Hazy"),
    SLIGHTLY_HAZY("Slightly Hazy"),
    WINDY("Windy"),
    MIST("Mist"),
    FOG("Fog"),
    LIGHT_RAIN("Light Rain"),
    MODERATE_RAIN("Moderate Rain"),
    HEAVY_RAIN("Heavy Rain"),
    PASSING_SHOWERS("Passing Showers"),
    LIGHT_SHOWERS("Light Showers"),
    SHOWERS("Showers"),
    HEAVY_SHOWERS("Heavy Showers"),
    THUNDERY_SHOWERS("Thundery Showers"),
    HEAVY_THUNDERY_SHOWERS("Heavy Thundery Showers"),
    HEAVY_THUNDERY_SHOWERS_WITH_GUSTY_WINDS("Heavy Thundery Showers With Gusty Winds");

    companion object {
        fun getImageResource(weatherText: WeatherText): Int = when (weatherText) {
            FAIR, FAIR_DAY, FAIR_NIGHT, FAIR_AND_WARM -> R.drawable.image_sun_cloud
            PARTLY_CLOUDY, PARTLY_CLOUDY_DAY, PARTLY_CLOUDY_NIGHT -> R.drawable.image_cloud_sun
            HEAVY_RAIN, HEAVY_SHOWERS -> R.drawable.image_heavy_rain
            CLOUDY -> R.drawable.image_cloudy
            WINDY -> R.drawable.image_windy_cloud
            LIGHT_RAIN, MODERATE_RAIN, PASSING_SHOWERS, LIGHT_SHOWERS, SHOWERS -> R.drawable.image_light_rain
            THUNDERY_SHOWERS, HEAVY_THUNDERY_SHOWERS, HEAVY_THUNDERY_SHOWERS_WITH_GUSTY_WINDS -> R.drawable.image_thunder_rain
            MIST -> R.drawable.image_misty
            HAZY, SLIGHTLY_HAZY, FOG -> R.drawable.image_hazy
        }
    }
}