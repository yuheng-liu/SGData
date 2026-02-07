package com.liuyuheng.sgdata.presentation.carparkavailability

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.liuyuheng.sgdata.domain.usecase.GetCarparkInfoDatasetUseCase
import com.liuyuheng.sgdata.domain.usecase.UpdateCarparkInfoDatasetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarparkAvailabilityViewModel @Inject constructor(
    private val updateCarparkInfoDatasetUseCase: UpdateCarparkInfoDatasetUseCase,
    getCarparkInfoDatasetUseCase: GetCarparkInfoDatasetUseCase
) : ViewModel() {

    private val _queryString = MutableStateFlow("")

    @OptIn(FlowPreview::class)
    val queryString = _queryString
        .debounce(300)
        .distinctUntilChanged().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ""
        )

    val filteredCarparkInfoList = combine(
        _queryString,
        getCarparkInfoDatasetUseCase(),
    ) { queryString, carparkInfoList ->
        if (queryString.isEmpty()) {
            carparkInfoList
        } else {
            carparkInfoList.filter { carparkInfo ->
                carparkInfo.carparkId.contains(queryString, ignoreCase = true)
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun updateCarparkInfoDataset() = viewModelScope.launch {
        updateCarparkInfoDatasetUseCase()
    }

    fun onQueryStringChanged(query: String) {
        _queryString.value = query
    }
}