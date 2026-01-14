package com.liuyuheng.sgdata.data.network.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    val code: Int,
    val name: String = "",
    val errorMsg: String = "",
)