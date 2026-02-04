package com.liuyuheng.sgdata.data.network.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BaseResponse<T>(
    val code: Int,
    val data: T? = null,
)