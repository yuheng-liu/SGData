package com.liuyuheng.sgdata.presentation.carparkavailability

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.liuyuheng.sgdata.presentation.carparkavailability.carparkinfo.CarparkInfoScreen
import com.liuyuheng.sgdata.presentation.main.navigation.AppRoute

fun NavGraphBuilder.carparkAvailabilityGraph(navController: NavHostController) {
    navigation(
        startDestination = CarparkAvailabilityRoute.CarparkAvailability.route,
        route = AppRoute.CarparkAvailability.route,
    ) {
        // Carpark Availability screen
        composable(CarparkAvailabilityRoute.CarparkAvailability.route) {
            CarparkAvailabilityScreen(
                viewModel = carparkAvailabilityViewModel(navController),
                onNavigateToCarparkInfo = {
                    navController.navigate(CarparkAvailabilityRoute.CarparkInfo.route)
                }
            )
        }
        // Carpark Info Screen
        composable(CarparkAvailabilityRoute.CarparkInfo.route) {
            CarparkInfoScreen(carparkAvailabilityViewModel(navController))
        }
    }
}

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
private fun carparkAvailabilityViewModel(
    navController: NavController
): CarparkAvailabilityViewModel {
    val carparkAvailabilityEntry = remember(navController) {
        navController.getBackStackEntry(AppRoute.CarparkAvailability.route)
    }
    return hiltViewModel(carparkAvailabilityEntry)
}