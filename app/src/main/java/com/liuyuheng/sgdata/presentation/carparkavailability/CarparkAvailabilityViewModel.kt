package com.liuyuheng.sgdata.presentation.carparkavailability

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.liuyuheng.sgdata.domain.model.carpark.CarparkInfo
import com.liuyuheng.sgdata.domain.usecase.GetCarparkInfoDatasetUseCase
import com.liuyuheng.sgdata.domain.usecase.UpdateCarparkInfoDatasetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarparkAvailabilityViewModel @Inject constructor(
    private val updateCarparkInfoDatasetUseCase: UpdateCarparkInfoDatasetUseCase,
    getCarparkInfoDatasetUseCase: GetCarparkInfoDatasetUseCase
) : ViewModel() {

    private val queryString = MutableStateFlow("")

    val carparkInfoList: StateFlow<List<CarparkInfo>> =
        getCarparkInfoDatasetUseCase.invoke()
            .stateIn(
                scope = viewModelScope,
                started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    fun updateCarparkInfoDataset() = viewModelScope.launch {
        updateCarparkInfoDatasetUseCase.invoke()
    }

    fun onQueryStringChanged(query: String) {
        queryString.value = query
    }
}