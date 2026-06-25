package com.liuyuheng.sgdata.core.data.models.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DatasetDownloadDto(
    val message: String,
    val url: String
)
