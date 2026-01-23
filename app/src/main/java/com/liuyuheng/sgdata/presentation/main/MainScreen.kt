package com.liuyuheng.sgdata.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.liuyuheng.sgdata.presentation.main.navigation.AppNavHost
import com.liuyuheng.sgdata.presentation.main.theme.SGDataTheme
import com.liuyuheng.sgdata.presentation.shared.loader.GlobalLoader
import com.liuyuheng.sgdata.presentation.shared.loader.LoadingStateHandler
import com.liuyuheng.sgdata.presentation.shared.topbar.SGDataTopBar
import com.liuyuheng.sgdata.presentation.shared.topbar.TopBarState
import com.liuyuheng.sgdata.presentation.shared.topbar.getTopBarState

@Composable
fun MainScreen(
    loadingStateHandler: LoadingStateHandler,
) {
    val navController = rememberNavController()
    val isLoading by loadingStateHandler.isLoading.collectAsStateWithLifecycle()

    // Top bar state
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val topBarState = getTopBarState(currentRoute, navController)

    SGDataTheme {
        Scaffold(
            topBar = {
                when (topBarState) {
                    is TopBarState.Hidden -> {}
                    is TopBarState.Shown -> SGDataTopBar(
                        title = topBarState.title,
                        onBackClicked = topBarState.onBackClicked,
                        actions = topBarState.actions
                    )
                }
            },
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.systemBars)
                .background(MaterialTheme.colorScheme.background)
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