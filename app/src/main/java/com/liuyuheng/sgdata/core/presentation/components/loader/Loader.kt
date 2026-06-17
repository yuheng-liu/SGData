package com.liuyuheng.sgdata.core.presentation.components.loader

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.liuyuheng.sgdata.core.presentation.components.BasePreviewComposable
import com.liuyuheng.sgdata.core.presentation.theme.onBackgroundLight

@Composable
fun Loader() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(onBackgroundLight.copy(alpha = 0.2f)),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
fun Loader_Preview() {
    BasePreviewComposable {
        Loader()
    }
}