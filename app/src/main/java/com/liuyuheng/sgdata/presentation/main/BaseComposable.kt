package com.liuyuheng.sgdata.presentation.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.liuyuheng.sgdata.presentation.main.theme.SGDataTheme

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