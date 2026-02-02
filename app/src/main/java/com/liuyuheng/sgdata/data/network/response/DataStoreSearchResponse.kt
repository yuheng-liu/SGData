package com.liuyuheng.sgdata.data.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataStoreSearchResponse<T>(
    val help: String,
    val success: Boolean,
    val result: Result<T>
) {
    @JsonClass(generateAdapter = true)
    data class Result<T>(
        @Json(name = "resource_id") val resourceId: String,
        val fields: List<Field>,
        val records: List<T>,
        @Json(name = "_links") val links: Link,
        val total: Int
    ) {
        @JsonClass(generateAdapter = true)
        data class Field(
            val type: String,
            val id: String,
        )

        @JsonClass(generateAdapter = true)
        data class Link(
            val start: String,
            val next: String
        )
    }
}