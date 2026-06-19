package com.liuyuheng.sgdata.carparkavailability.presentation

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.liuyuheng.sgdata.carparkavailability.domain.models.CarparkInfo
import com.liuyuheng.sgdata.carparkavailability.domain.usecases.GetCarparkAvailabilityUseCase
import com.liuyuheng.sgdata.carparkavailability.domain.usecases.GetCarparkInfoDatasetUseCase
import com.liuyuheng.sgdata.carparkavailability.domain.usecases.UpdateCarparkInfoDatasetUseCase
import com.liuyuheng.sgdata.carparkavailability.presentation.carparkinfo.CarparkInfoUiState
import com.liuyuheng.sgdata.core.domain.models.ApiResult
import com.liuyuheng.sgdata.core.domain.usecases.GetMetadataUseCase
import com.liuyuheng.sgdata.core.utils.toDisplayDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class CarparkAvailabilityViewModel @Inject constructor(
    private val getMetadataUseCase: GetMetadataUseCase,
    getCarparkInfoDatasetUseCase: GetCarparkInfoDatasetUseCase,
    private val updateCarparkInfoDatasetUseCase: UpdateCarparkInfoDatasetUseCase,
    private val getCarparkAvailabilityUseCase: GetCarparkAvailabilityUseCase,
) : ViewModel() {

    private val queryString = MutableStateFlow(TextFieldValue())

    @OptIn(FlowPreview::class)
    private val debouncedQuery = queryString
        .map { it.text }
        .distinctUntilChanged()
        .debounce(QUERY_DEBOUNCE_TIME.milliseconds)

    val carparkInfoUiState = combine(
        queryString,
        debouncedQuery,
        getCarparkInfoDatasetUseCase(),
    ) { queryString, debouncedQuery, carparkInfoList ->
        CarparkInfoUiState(
            filteredCarparkInfoList = filterCarparkList(debouncedQuery, carparkInfoList),
            queryString = queryString,
            lastUpdated = getMetadataUseCase()?.toDisplayDate() ?: ""
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = CarparkInfoUiState()
    )

    init {
        viewModelScope.launch {
            updateCarparkInfoDatasetUseCase.ensureDatabaseUpToDate()
            fetchCarparkAvailability()
        }
    }

    private fun fetchCarparkAvailability() = viewModelScope.launch {
        when (val carparkAvailability = getCarparkAvailabilityUseCase()) {
            is ApiResult.Success -> {

            }

            is ApiResult.Error -> {

            }
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

    fun onQueryStringChanged(query: TextFieldValue) {
        queryString.value = query
    }

    private companion object {
        const val QUERY_DEBOUNCE_TIME = 500L
    }
}