package com.liuyuheng.sgdata.domain.shared

fun temperatureUnitToSymbol(unit: String?): String {
    return when (unit) {
        "Degrees Celsius" -> "°C"
        "Degrees Fahrenheit" -> "°F"
        else -> ""
    }
}