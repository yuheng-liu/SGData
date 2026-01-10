package com.liuyuheng.sgdata.presentation.shared.loader

import com.liuyuheng.sgdata.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class LoadingStateHandler {

    private val _loadingCount = MutableStateFlow(0)
    val isLoading = _loadingCount
        .map { it > 0 }
        .stateIn(
            scope = CoroutineScope(SupervisorJob() + Dispatchers.Main),
            started = SharingStarted.WhileSubscribed(Constants.WHILE_SUBSCRIBED_TIMEOUT_MS),
            initialValue = false,
        )

    fun show() {
        _loadingCount.update { it + 1 }
    }

    fun hide() {
        _loadingCount.update { maxOf(0, it - 1) }
    }
}

suspend fun <T> LoadingStateHandler.withLoading(block: suspend () -> T): T {
    show()
    return try {
        block()
    } finally {
        hide()
    }
}