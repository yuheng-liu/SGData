package com.liuyuheng.sgdata.presentation.carparkavailability

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.liuyuheng.sgdata.domain.model.carpark.CarparkInfo
import com.liuyuheng.sgdata.domain.usecase.GetCarparkInfoDatasetUseCase
import com.liuyuheng.sgdata.domain.usecase.GetMetadataUseCase
import com.liuyuheng.sgdata.domain.usecase.UpdateCarparkInfoDatasetUseCase
import com.liuyuheng.sgdata.presentation.carparkavailability.carparkinfo.CarparkInfoUiState
import com.liuyuheng.sgdata.shared.toDisplayDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarparkAvailabilityViewModel @Inject constructor(
    private val updateCarparkInfoDatasetUseCase: UpdateCarparkInfoDatasetUseCase,
    getCarparkInfoDatasetUseCase: GetCarparkInfoDatasetUseCase,
    private val getMetadataUseCase: GetMetadataUseCase
) : ViewModel() {

    private val queryString = MutableStateFlow("")

    val uiState = combine(
        queryString,
        getCarparkInfoDatasetUseCase(),
    ) { queryString, carparkInfoList ->
        CarparkInfoUiState(
            filteredCarparkInfoList = filterCarparkList(queryString, carparkInfoList),
            queryString = queryString,
            lastUpdated = getMetadataUseCase()?.toDisplayDate() ?: ""
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = CarparkInfoUiState()
    )

    init {
        viewModelScope.launch(Dispatchers.IO) {
            updateCarparkInfoDatasetUseCase.ensureDatabaseUpToDate()
        }
    }

    private fun filterCarparkList(queryString: String, carparkInfoList: List<CarparkInfo>): List<CarparkInfo> {
        return if (queryString.isEmpty()) {
            carparkInfoList
        } else {
            carparkInfoList.filter { carparkInfo ->
                carparkInfo.carparkId.contains(queryString, ignoreCase = true) ||
                        carparkInfo.address.contains(queryString, ignoreCase = true)
            }
        }
    }

    fun updateCarparkInfoDataset() = viewModelScope.launch {
        updateCarparkInfoDatasetUseCase.updateCarparkInfoDataset()
    }

    fun onQueryStringChanged(query: String) {
        queryString.value = query
    }
}