package com.liuyuheng.sgdata.presentation.shared.topbar

sealed class TopBarState {
    data object Hidden : TopBarState()
    data class Shown(
        val title: String = "",
        val onBackClicked: (() -> Unit)? = null,
    ) : TopBarState()
}