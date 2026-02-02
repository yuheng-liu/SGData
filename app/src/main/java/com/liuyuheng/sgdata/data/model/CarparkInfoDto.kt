package com.liuyuheng.sgdata.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CarparkInfoDto(
    @Json(name = "_id") val id: Int,
    @Json(name = "car_park_no") val carparkNumber: String,
    val address: String,
    @Json(name = "x_coord") val xCoordinate: String,
    @Json(name = "y_coord") val yCoordinate: String,
    @Json(name = "car_park_type") val carparkType: String,
    @Json(name = "type_of_parking_system") val typeOfParkingSystem: String,
    @Json(name = "short_term_parking") val shortTermParking: String,
    @Json(name = "free_parking") val freeParking: String,
    @Json(name = "night_parking") val nightParking: String,
    @Json(name = "car_park_decks") val carparkDecks: String,
    @Json(name = "gantry_height") val gantryHeight: String,
    @Json(name = "car_park_basement") val carparkBasement: String,
)
