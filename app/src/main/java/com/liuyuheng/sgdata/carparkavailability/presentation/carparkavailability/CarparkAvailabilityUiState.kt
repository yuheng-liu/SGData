package com.liuyuheng.sgdata.carparkavailability.presentation.carparkavailability

import com.liuyuheng.sgdata.carparkavailability.domain.models.CarparkInfo

sealed class CarparkAvailabilityUiState {
    data object Idle : CarparkAvailabilityUiState()
    data object Loading : CarparkAvailabilityUiState()
    data class Loaded(val data: List<CarparkInfo>) : CarparkAvailabilityUiState()
    data class Error(val message: String) : CarparkAvailabilityUiState()
}
