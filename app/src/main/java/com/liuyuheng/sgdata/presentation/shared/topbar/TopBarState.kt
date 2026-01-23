package com.liuyuheng.sgdata.presentation.shared.topbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.liuyuheng.sgdata.presentation.main.navigation.AppRoute
import com.liuyuheng.sgdata.presentation.weatherforecast.WeatherForecastRoute

sealed class TopBarState {
    data object Hidden : TopBarState()
    data class Shown(
        val title: String = "",
        val onBackClicked: (() -> Unit)? = null,
        val actions: @Composable RowScope.() -> Unit = {}
    ) : TopBarState()
}

fun getTopBarState(
    route: String?,
    navController: NavController
) = when (route) {
    AppRoute.Home.route -> TopBarState.Shown(
        title = "SG Data",
        onBackClicked = null,
    )

    WeatherForecastRoute.FourDayForecast.route -> TopBarState.Shown(
        title = "4 Days Forecast",
        onBackClicked = { navController.popBackStack() },
    )

    WeatherForecastRoute.TwentyFourHourForecast.route -> TopBarState.Shown(
        title = "24hrs Forecast",
        onBackClicked = { navController.popBackStack() },
        actions = {
            IconButton(onClick = { navController.navigate(WeatherForecastRoute.TwoHourForecast.route) }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.List,
                    contentDescription = null
                )
            }
        }
    )

    WeatherForecastRoute.TwoHourForecast.route -> TopBarState.Shown(
        title = "2hrs Forecast",
        onBackClicked = { navController.popBackStack() },
    )

    else -> TopBarState.Hidden
}