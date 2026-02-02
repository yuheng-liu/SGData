package com.liuyuheng.sgdata.presentation.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.liuyuheng.sgdata.presentation.carparkavailability.carparkAvailabilityGraph
import com.liuyuheng.sgdata.presentation.home.HomeScreen
import com.liuyuheng.sgdata.presentation.weatherforecast.weatherForecastGraph

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
                    navHostController.navigate(AppRoute.WeatherForecast.route)
                },
                onNavigateToCarparkAvailability = {
                    navHostController.navigate(AppRoute.CarparkAvailability.route)
                }
            )
        }
        // WeatherForecast
        weatherForecastGraph(navHostController)
        // Carpark Availability
        carparkAvailabilityGraph(navHostController)
    }
}