package com.liuyuheng.sgdata.core.presentation

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
import com.liuyuheng.sgdata.core.presentation.components.loader.Loader
import com.liuyuheng.sgdata.core.presentation.components.loader.LoadingStateHandler
import com.liuyuheng.sgdata.core.presentation.components.topbar.SGDataTopBar
import com.liuyuheng.sgdata.core.presentation.components.topbar.TopBarState
import com.liuyuheng.sgdata.core.presentation.components.topbar.getTopBarState
import com.liuyuheng.sgdata.core.presentation.navigation.AppNavHost
import com.liuyuheng.sgdata.core.presentation.theme.SGDataTheme

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
                Loader()
            }
        }
    }
}