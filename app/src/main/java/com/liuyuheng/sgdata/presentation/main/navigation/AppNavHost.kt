package com.liuyuheng.sgdata.presentation.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.liuyuheng.sgdata.presentation.home.HomeScreen
import com.liuyuheng.sgdata.presentation.weatherforecast.WeatherForecastScreen

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
                }
            )
        }
        // WeatherForecast
        composable(AppRoute.WeatherForecast.route) {
            WeatherForecastScreen()
        }
    }
}