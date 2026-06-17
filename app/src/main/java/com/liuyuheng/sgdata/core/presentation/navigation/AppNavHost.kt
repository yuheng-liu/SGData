package com.liuyuheng.sgdata.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.liuyuheng.sgdata.carparkavailability.presentation.carparkAvailabilityGraph
import com.liuyuheng.sgdata.home.presentation.HomeScreen
import com.liuyuheng.sgdata.weatherforecast.presentation.weatherForecastGraph

@Composable
fun AppNavHost(
    navHostController: NavHostController,
    modifier: Modifier,
) {
    NavHost(
        navController = navHostController,
        startDestination = AppRoute.Home.route,
        modifier = modifier,
    ) {
        // Home
        composable(AppRoute.Home.route) {
            HomeScreen(
                onNavigateToWeatherForecast = {
                    navHostController.navigate(AppRoute.WeatherForecastGraph.route)
                },
                onNavigateToCarparkAvailability = {
                    navHostController.navigate(AppRoute.CarparkAvailabilityGraph.route)
                }
            )
        }
        // WeatherForecast
        weatherForecastGraph(navHostController)
        // Carpark Availability
        carparkAvailabilityGraph(navHostController)
    }
}