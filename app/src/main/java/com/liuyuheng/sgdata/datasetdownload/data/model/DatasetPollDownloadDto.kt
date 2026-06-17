package com.liuyuheng.sgdata.datasetdownload.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DatasetPollDownloadDto(
    val status: String,
    val url: String
)
