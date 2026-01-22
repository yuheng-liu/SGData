package com.liuyuheng.sgdata.presentation.shared.loader

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.liuyuheng.sgdata.presentation.main.BasePreviewComposable
import com.liuyuheng.sgdata.presentation.main.theme.onBackgroundLight

@Composable
fun GlobalLoader() {
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
fun GlobalLoader_Preview() {
    BasePreviewComposable {
        GlobalLoader()
    }
}