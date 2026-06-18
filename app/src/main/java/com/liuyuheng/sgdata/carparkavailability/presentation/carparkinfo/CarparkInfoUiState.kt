package com.liuyuheng.sgdata.carparkavailability.presentation.carparkinfo

import androidx.compose.ui.text.input.TextFieldValue
import com.liuyuheng.sgdata.carparkavailability.domain.models.CarparkInfo

data class CarparkInfoUiState(
    val filteredCarparkInfoList: List<CarparkInfo> = emptyList(),
    val queryString: TextFieldValue = TextFieldValue(),
    val lastUpdated: String = "",
)