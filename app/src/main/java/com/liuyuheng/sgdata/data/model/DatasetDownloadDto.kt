package com.liuyuheng.sgdata.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DatasetDownloadDto(
    val message: String,
    val url: String
)
