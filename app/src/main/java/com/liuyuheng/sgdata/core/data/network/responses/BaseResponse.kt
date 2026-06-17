package com.liuyuheng.sgdata.core.data.network.responses

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BaseResponse<T>(
    val code: Int,
    val data: T? = null,
)