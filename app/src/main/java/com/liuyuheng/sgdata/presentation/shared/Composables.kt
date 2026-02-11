package com.liuyuheng.sgdata.presentation.shared

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.liuyuheng.sgdata.presentation.main.theme.Dimensions

@Composable
fun SGDataSpacer(paddingSize: Dp = Dimensions.paddingMedium) {
    Spacer(
        Modifier
            .height(paddingSize)
            .width(paddingSize)
    )
}