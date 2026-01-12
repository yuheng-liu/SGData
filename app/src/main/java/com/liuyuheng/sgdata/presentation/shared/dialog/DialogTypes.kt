package com.liuyuheng.sgdata.presentation.shared.dialog

sealed class DialogTypes {
    data class HttpError(val message: String) : DialogTypes()
}