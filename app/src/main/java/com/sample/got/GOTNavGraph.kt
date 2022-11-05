package com.sample.got

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sample.got.housedetail.HouseDetailScreen
import com.sample.got.houses.HousesScreen

@Composable
fun GOTNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = GOTDestinations.HOUSES_ROUTE,
    navActions: GOTNavigationActions = remember(navController) {
        GOTNavigationActions(navController)
    }
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(GOTDestinations.HOUSES_ROUTE) {
            HousesScreen(
                onHouseClick = { house -> navActions.navigateToHouseDetail(house.url) }
            )
        }
        composable(
            GOTDestinations.HOUSE_DETAIL_ROUTE,
            arguments = listOf(navArgument(GOTDestinationsArgs.HOUSE_ID_ARG) {
                type = NavType.StringType
            })
        ) {
            HouseDetailScreen(
                onBack = { navController.popBackStack() },
            )
        }

    }
}