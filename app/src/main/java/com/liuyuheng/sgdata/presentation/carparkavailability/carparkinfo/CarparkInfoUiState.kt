package com.liuyuheng.sgdata.presentation.carparkavailability.carparkinfo

import com.liuyuheng.sgdata.domain.model.carpark.CarparkInfo

data class CarparkInfoUiState(
    val filteredCarparkInfoList: List<CarparkInfo> = emptyList(),
    val queryString: String = "",
    val lastUpdated: String = "",
)