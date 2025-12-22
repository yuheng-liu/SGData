package com.liuyuheng.sgdata.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.liuyuheng.sgdata.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _screensList = MutableStateFlow<List<ScreenType>>(emptyList())
    val screensList = _screensList
        .onStart { _screensList.value = ScreenType.entries }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            emptyList(),
        )
}

enum class ScreenType(val displayName: String, val icon: Int) {
    WEATHER_FORECAST("Weather Forecast", R.drawable.image_weather),
}