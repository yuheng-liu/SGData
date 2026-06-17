package com.liuyuheng.sgdata.datasetdownload.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DatasetDownloadDto(
    val message: String,
    val url: String
)
