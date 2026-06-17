package com.liuyuheng.sgdata.carparkavailability.presentation.carparkinfo

import com.liuyuheng.sgdata.carparkavailability.domain.models.CarparkInfo

data class CarparkInfoUiState(
    val filteredCarparkInfoList: List<CarparkInfo> = emptyList(),
    val queryString: String = "",
    val lastUpdated: String = "",
)