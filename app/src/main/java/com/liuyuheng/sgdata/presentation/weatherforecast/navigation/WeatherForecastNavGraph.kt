package com.liuyuheng.sgdata.presentation.weatherforecast.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.liuyuheng.sgdata.presentation.main.navigation.AppRoute
import com.liuyuheng.sgdata.presentation.weatherforecast.WeatherForecastViewModel
import com.liuyuheng.sgdata.presentation.weatherforecast.fourday.FourDayForecastScreen
import com.liuyuheng.sgdata.presentation.weatherforecast.twentyfourhour.TwentyFourHoursForecastScreen

fun NavGraphBuilder.weatherForecastGraph(navController: NavHostController) {
    navigation(
        startDestination = WeatherForecastRoute.FourDayForecast.route,
        route = AppRoute.WeatherForecast.route,
    ) {
        // Four Day Forecast screen, initial screen
        composable(WeatherForecastRoute.FourDayForecast.route) {
            FourDayForecastScreen(
                weatherForecastViewModel(navController),
                onNavigateToTwentyFourHourForecastScreen = {
                    navController.navigate(WeatherForecastRoute.TwentyFourHourForecast.route)
                }
            )
        }
        // Twenty Four Hours Forecast screen
        composable(WeatherForecastRoute.TwentyFourHourForecast.route) {
            TwentyFourHoursForecastScreen(weatherForecastViewModel(navController))
        }
    }
}

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
private fun weatherForecastViewModel(
    navController: NavController
): WeatherForecastViewModel {
    val weatherForecastEntry = remember(navController) {
        navController.getBackStackEntry(AppRoute.WeatherForecast.route)
    }
    return hiltViewModel(weatherForecastEntry)
}