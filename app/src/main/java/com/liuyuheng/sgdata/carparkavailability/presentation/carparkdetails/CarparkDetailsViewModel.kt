package com.liuyuheng.sgdata.carparkavailability.presentation.carparkdetails

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.liuyuheng.sgdata.carparkavailability.domain.models.CarparkInfo
import com.liuyuheng.sgdata.carparkavailability.domain.usecases.GetCarparkDetailsUseCase
import com.liuyuheng.sgdata.carparkavailability.domain.usecases.UpdateCarparkDetailsUseCase
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
class CarparkDetailsViewModel @Inject constructor(
    private val getMetadataUseCase: GetMetadataUseCase,
    getCarparkDetailsUseCase: GetCarparkDetailsUseCase,
    private val updateCarparkDetailsUseCase: UpdateCarparkDetailsUseCase,
) : ViewModel() {

    private val queryString = MutableStateFlow(TextFieldValue())

    @OptIn(FlowPreview::class)
    private val debouncedQuery = queryString
        .map { it.text }
        .distinctUntilChanged()
        .debounce(QUERY_DEBOUNCE_TIME.milliseconds)

    val carparkDetailsUiState = combine(
        queryString,
        debouncedQuery,
        getCarparkDetailsUseCase(),
    ) { queryString, debouncedQuery, carparkInfoList ->
        CarparkDetailsUiState(
            filteredCarparkInfoList = filterCarparkList(debouncedQuery, carparkInfoList),
            queryString = queryString,
            lastUpdated = getMetadataUseCase()?.toDisplayDate() ?: ""
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = CarparkDetailsUiState()
    )

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
        updateCarparkDetailsUseCase.invoke()
    }

    fun onQueryStringChanged(query: TextFieldValue) {
        queryString.value = query
    }

    private companion object {
        const val QUERY_DEBOUNCE_TIME = 500L
    }
}