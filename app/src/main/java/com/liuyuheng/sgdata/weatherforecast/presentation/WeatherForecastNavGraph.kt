package com.liuyuheng.sgdata.weatherforecast.presentation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.liuyuheng.sgdata.core.presentation.navigation.AppRoute
import com.liuyuheng.sgdata.weatherforecast.presentation.fourday.FourDayForecastScreen
import com.liuyuheng.sgdata.weatherforecast.presentation.twentyfourhour.TwentyFourHourForecastScreen
import com.liuyuheng.sgdata.weatherforecast.presentation.twohour.TwoHourForecastScreen

fun NavGraphBuilder.weatherForecastGraph(navController: NavHostController) {
    navigation(
        startDestination = WeatherForecastRoute.TwentyFourHourForecast.route,
        route = AppRoute.WeatherForecastGraph.route,
    ) {
        composable(WeatherForecastRoute.TwentyFourHourForecast.route) {
            TwentyFourHourForecastScreen(
                viewModel = weatherForecastViewModel(navController),
                onNavigateToTwoHourForecast = {
                    navController.navigate(WeatherForecastRoute.TwoHourForecast.route)
                },
                onNavigateToFourDayForecast = {
                    navController.navigate(WeatherForecastRoute.FourDayForecast.route)
                }
            )
        }
        composable(WeatherForecastRoute.TwoHourForecast.route) {
            TwoHourForecastScreen(
                weatherForecastViewModel(navController)
            )
        }
        composable(WeatherForecastRoute.FourDayForecast.route) {
            FourDayForecastScreen(
                weatherForecastViewModel(navController)
            )
        }
    }
}

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
private fun weatherForecastViewModel(
    navController: NavController
): WeatherForecastViewModel {
    val weatherForecastEntry = remember(navController) {
        navController.getBackStackEntry(AppRoute.WeatherForecastGraph.route)
    }
    return hiltViewModel(weatherForecastEntry)
}