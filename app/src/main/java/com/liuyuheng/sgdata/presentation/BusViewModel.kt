package com.liuyuheng.sgdata.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.liuyuheng.sgdata.domain.entity.Bus
import com.liuyuheng.sgdata.domain.usecase.GetBusesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BusViewModel @Inject constructor(
    private val getBusesUseCase: GetBusesUseCase
) : ViewModel() {

    private val _buses = MutableStateFlow<List<Bus>>(emptyList())
    val buses: StateFlow<List<Bus>> = _buses

    fun loadBuses() {
        viewModelScope.launch {
            _buses.value = getBusesUseCase.execute()
        }
    }
}