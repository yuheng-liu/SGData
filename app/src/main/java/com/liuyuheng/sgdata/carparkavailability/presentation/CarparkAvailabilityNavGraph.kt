package com.liuyuheng.sgdata.carparkavailability.presentation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.liuyuheng.sgdata.carparkavailability.presentation.carparkavailability.CarparkAvailabilityScreen
import com.liuyuheng.sgdata.carparkavailability.presentation.carparkinfo.CarparkInfoScreen
import com.liuyuheng.sgdata.core.presentation.navigation.AppRoute

fun NavGraphBuilder.carparkAvailabilityGraph(navController: NavHostController) {
    navigation(
        startDestination = CarparkAvailabilityRoute.CarparkAvailability.route,
        route = AppRoute.CarparkAvailabilityGraph.route,
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
        navController.getBackStackEntry(AppRoute.CarparkAvailabilityGraph.route)
    }
    return hiltViewModel(carparkAvailabilityEntry)
}