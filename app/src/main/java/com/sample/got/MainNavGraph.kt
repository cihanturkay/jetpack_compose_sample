package com.sample.got

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sample.got.data.model.getId
import com.sample.got.housedetail.HouseDetailScreen
import com.sample.got.houses.HousesScreen

@Composable
fun MainNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destinations.HOUSES_ROUTE,
    navActions: NavigationActions = remember(navController) {
        NavigationActions(navController)
    }
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(Destinations.HOUSES_ROUTE) {
            HousesScreen(
                onHouseClick = { house -> navActions.navigateToHouseDetail(house.getId()) }
            )
        }
        composable(
            Destinations.HOUSE_DETAIL_ROUTE,
            arguments = listOf(navArgument(DestinationsArgs.HOUSE_ID_ARG) {
                type = NavType.IntType
            })
        ) {
            HouseDetailScreen(
                onBack = { navController.popBackStack() },
            )
        }
    }
}