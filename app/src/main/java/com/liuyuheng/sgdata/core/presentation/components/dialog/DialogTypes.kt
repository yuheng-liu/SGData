package com.liuyuheng.sgdata.core.presentation.components.dialog

sealed class DialogTypes {
    data class HttpError(val message: String) : DialogTypes()
}