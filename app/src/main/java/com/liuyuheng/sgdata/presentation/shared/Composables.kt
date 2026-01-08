package com.liuyuheng.sgdata.presentation.shared

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.liuyuheng.sgdata.presentation.main.theme.Dimensions

@Composable
fun SGDataSpacer() {
    Spacer(
        Modifier
            .height(Dimensions.paddingMedium)
            .width(Dimensions.paddingMedium)
    )
}