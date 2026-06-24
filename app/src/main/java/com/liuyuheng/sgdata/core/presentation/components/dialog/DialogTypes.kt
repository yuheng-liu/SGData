package com.liuyuheng.sgdata.core.presentation.components.dialog

sealed class DialogTypes {
    data class SimpleError(val message: String) : DialogTypes()
}