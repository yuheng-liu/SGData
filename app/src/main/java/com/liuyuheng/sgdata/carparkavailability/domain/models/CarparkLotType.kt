package com.liuyuheng.sgdata.carparkavailability.domain.models

/**
 * Retrieved From https://data.gov.sg/datasets?formats=API&sort=relevancy&resultId=d_ca933a644e55d34fe21f28b8052fac63#GET/transport/carpark-availability
 */
enum class CarparkLotType(val code: String) {
    UNKNOWN(""),
    CARS("C"),
    HEAVY_VEHICLES("H"),
    MOTORCYCLES("Y"),
    MOTORCYCLES_WITH_SIDE_CAR("S"),
}