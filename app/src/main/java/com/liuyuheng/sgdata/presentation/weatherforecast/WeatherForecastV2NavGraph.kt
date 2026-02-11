package com.liuyuheng.sgdata.presentation.weatherforecast

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
import com.liuyuheng.sgdata.presentation.weatherforecast.fourday.FourDayForecastV2Screen
import com.liuyuheng.sgdata.presentation.weatherforecast.twentyfourhour.TwentyFourHourForecastV2Screen

fun NavGraphBuilder.weatherForecastV2Graph(navController: NavHostController) {
    navigation(
        startDestination = WeatherForecastRoute.V2TwentyFourHourForecast.route,
        route = AppRoute.WeatherForecastV2Graph.route,
    ) {
        composable(WeatherForecastRoute.V2TwentyFourHourForecast.route) {
            TwentyFourHourForecastV2Screen(
                viewModel = weatherForecastViewModel(navController),
                onNavigateToFourDayForecast = {
                    navController.navigate(WeatherForecastRoute.V2FourDayForecast.route)
                }
            )
        }
        composable(WeatherForecastRoute.V2FourDayForecast.route) {
            FourDayForecastV2Screen(
                weatherForecastViewModel(navController)
            )
        }
    }
}

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
private fun weatherForecastViewModel(
    navController: NavController
): WeatherForecastV2ViewModel {
    val weatherForecastEntry = remember(navController) {
        navController.getBackStackEntry(AppRoute.WeatherForecastV2Graph.route)
    }
    return hiltViewModel(weatherForecastEntry)
}