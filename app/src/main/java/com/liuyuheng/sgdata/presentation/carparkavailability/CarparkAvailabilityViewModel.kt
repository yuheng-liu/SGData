package com.liuyuheng.sgdata.presentation.carparkavailability

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.liuyuheng.sgdata.domain.usecase.GetCarparkInfoDatasetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarparkAvailabilityViewModel @Inject constructor(
    private val getCarparkInfoDatasetUseCase: GetCarparkInfoDatasetUseCase
) : ViewModel() {

    fun getCarparkInfoDataset() = viewModelScope.launch {
        getCarparkInfoDatasetUseCase.invoke()
    }
}