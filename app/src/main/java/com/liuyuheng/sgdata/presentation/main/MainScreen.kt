package com.liuyuheng.sgdata.presentation.main

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.liuyuheng.sgdata.presentation.main.navigation.AppNavHost
import com.liuyuheng.sgdata.presentation.main.theme.SGDataTheme
import com.liuyuheng.sgdata.presentation.shared.loader.GlobalLoader
import com.liuyuheng.sgdata.presentation.shared.loader.LoadingStateHandler

@Composable
fun MainScreen(
    loadingStateHandler: LoadingStateHandler,
) {
    val navController = rememberNavController()
    val isLoading by loadingStateHandler.isLoading.collectAsStateWithLifecycle()

    SGDataTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.systemBars)
        ) { innerPadding ->
            AppNavHost(
                navHostController = navController,
                modifier = Modifier.padding(innerPadding),
            )

            if (isLoading) {
                GlobalLoader()
            }
        }
    }
}