package com.liuyuheng.sgdata.carparkavailability.presentation.carparkavailability

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.liuyuheng.sgdata.carparkavailability.domain.usecases.GetCarparkAvailabilityUseCase
import com.liuyuheng.sgdata.core.domain.models.DomainResult
import com.liuyuheng.sgdata.core.presentation.components.dialog.DialogTypes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarparkAvailabilityViewModel @Inject constructor(
    private val getCarparkAvailabilityUseCase: GetCarparkAvailabilityUseCase,
) : ViewModel() {

    private val _carparkAvailabilityUiState = MutableStateFlow<CarparkAvailabilityUiState>(CarparkAvailabilityUiState.Idle)
    val carparkParkAvailabilityUiState = _carparkAvailabilityUiState.asStateFlow()

    private val _dialogState = MutableStateFlow<DialogTypes?>(null)
    val dialogState = _dialogState.asStateFlow()

    init {
        viewModelScope.launch {
            _carparkAvailabilityUiState.value = CarparkAvailabilityUiState.Loading
            getCarparkAvailabilityUseCase.fetchCarparkAvailability()
            observeCarparkAvailabilityStream()
        }
    }

    private fun observeCarparkAvailabilityStream() = viewModelScope.launch {
        getCarparkAvailabilityUseCase.getCarparkAvailabilityStream().collect { result ->
            when (result) {
                is DomainResult.Success -> {
                    _carparkAvailabilityUiState.value = CarparkAvailabilityUiState.Loaded(
                        data = result.data
                    )
                }

                is DomainResult.Failure.NoData -> {
                    _carparkAvailabilityUiState.value = CarparkAvailabilityUiState.Error(result.message)
                    _dialogState.value = DialogTypes.SimpleError(result.message)
                }

                is DomainResult.Failure.Unavailable -> {
                    _carparkAvailabilityUiState.value = CarparkAvailabilityUiState.Error(result.message)
                    _dialogState.value = DialogTypes.SimpleError(result.message)
                }
            }
        }
    }

    fun onDismissErrorDialog() {
        _dialogState.value = null
    }
}