package com.liuyuheng.sgdata.carparkavailability.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.liuyuheng.sgdata.carparkavailability.presentation.carparkavailability.CarparkAvailabilityScreen
import com.liuyuheng.sgdata.carparkavailability.presentation.carparkdetails.CarparkDetailsScreen
import com.liuyuheng.sgdata.core.presentation.navigation.AppRoute

fun NavGraphBuilder.carparkAvailabilityGraph(navController: NavHostController) {
    navigation(
        startDestination = CarparkAvailabilityRoute.CarparkAvailability.route,
        route = AppRoute.CarparkAvailabilityGraph.route,
    ) {
        // Carpark Availability screen
        composable(CarparkAvailabilityRoute.CarparkAvailability.route) {
            CarparkAvailabilityScreen(
                onNavigateToCarparkDetails = {
                    navController.navigate(CarparkAvailabilityRoute.CarparkDetails.route)
                }
            )
        }
        // Carpark Info Screen
        composable(CarparkAvailabilityRoute.CarparkDetails.route) {
            CarparkDetailsScreen()
        }
    }
}