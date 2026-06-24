package com.liuyuheng.sgdata.core.presentation.components.topbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.liuyuheng.sgdata.carparkavailability.presentation.CarparkAvailabilityRoute
import com.liuyuheng.sgdata.core.presentation.navigation.AppRoute
import com.liuyuheng.sgdata.weatherforecast.presentation.WeatherForecastRoute

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
    // Home
    AppRoute.Home.route -> TopBarState.Shown(
        title = "SG Data",
        onBackClicked = null,
    )

    // Weather Forecast
    WeatherForecastRoute.TwentyFourHourForecast.route -> TopBarState.Shown(
        title = "24hrs Forecast",
        onBackClicked = { navController.popBackStack() },
    )
    WeatherForecastRoute.TwoHourForecast.route -> TopBarState.Shown(
        title = "2hrs Forecast",
        onBackClicked = { navController.popBackStack() },
    )
    WeatherForecastRoute.FourDayForecast.route -> TopBarState.Shown(
        title = "4 Days Forecast",
        onBackClicked = { navController.popBackStack() },
    )

    // Carpark Availability
    CarparkAvailabilityRoute.CarparkAvailability.route -> TopBarState.Shown(
        title = "Carpark Availability",
        onBackClicked = { navController.popBackStack() }
    )
    CarparkAvailabilityRoute.CarparkDetails.route -> TopBarState.Shown(
        title = "Carpark Details",
        onBackClicked = { navController.popBackStack() }
    )

    else -> TopBarState.Hidden
}