package com.liuyuheng.sgdata.data

inline fun <reified T : Enum<T>> String?.toEnumOrNull(): T? {
    return this?.let {
        try {
            enumValueOf<T>(it.trim())
        } catch (_: IllegalStateException) {
            null
        }
    }
}

fun String?.toBooleanOrNull(): Boolean? {
    return this?.let {
        when (it.trim().lowercase()) {
            "y", "yes" -> true
            "n", "no" -> false
            else -> null
        }
    }
}