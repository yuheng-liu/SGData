package com.liuyuheng.sgdata.core.data.network.responses

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    val code: Int,
    val name: String = "",
    val errorMsg: String = "",
)