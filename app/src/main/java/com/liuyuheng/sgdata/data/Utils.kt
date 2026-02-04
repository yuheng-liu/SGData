package com.liuyuheng.sgdata.data

fun String?.toBooleanOrNull(): Boolean? {
    return this?.let {
        when (it.trim().lowercase()) {
            "y", "yes" -> true
            "n", "no" -> false
            else -> null
        }
    }
}