package com.liuyuheng.sgdata.core.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.liuyuheng.sgdata.core.presentation.theme.Dimensions
import com.liuyuheng.sgdata.core.presentation.theme.SGDataTheme

@Composable
fun SGDataSpacer(paddingSize: Dp = Dimensions.paddingMedium) {
    Spacer(
        Modifier
            .height(paddingSize)
            .width(paddingSize)
    )
}

@Composable
fun BasePreviewComposable(
    content: @Composable (PaddingValues) -> Unit,
) {
    SGDataTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            content(innerPadding)
        }
    }
}