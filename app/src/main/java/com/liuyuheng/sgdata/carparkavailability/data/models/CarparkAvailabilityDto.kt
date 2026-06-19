package com.liuyuheng.sgdata.carparkavailability.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CarparkAvailabilityDto(
    val items: List<CarparkAvailabilityData>
) {
    @JsonClass(generateAdapter = true)
    data class CarparkAvailabilityData(
        val timestamp: String,
        @Json(name = "carpark_data") val carparkData: List<CarparkData>,
    ) {
        @JsonClass(generateAdapter = true)
        data class CarparkData(
            @Json(name = "update_datetime") val updatedDateTime: String,
            @Json(name = "carpark_number") val carparkNumber: String,
            @Json(name = "carpark_info") val carparkAvailabilityInfo: List<CarparkAvailabilityInfo>
        ) {
            @JsonClass(generateAdapter = true)
            data class CarparkAvailabilityInfo(
                @Json(name = "lots_available") val lotsAvailable: String,
                @Json(name = "lot_type") val lotType: String,
                @Json(name = "total_lots") val totalLots: String,
            )
        }
    }
}