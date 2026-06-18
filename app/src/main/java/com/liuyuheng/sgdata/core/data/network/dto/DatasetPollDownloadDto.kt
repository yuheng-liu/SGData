package com.liuyuheng.sgdata.core.data.network.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DatasetPollDownloadDto(
    val status: String,
    val url: String
)
