package com.sample.got

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.sample.got.DestinationsArgs.HOUSE_ID_ARG
import com.sample.got.Screens.HOUSES_SCREEN
import com.sample.got.Screens.HOUSE_DETAIL_SCREEN

private object Screens {
    const val HOUSES_SCREEN = "houses"
    const val HOUSE_DETAIL_SCREEN = "houseDetail"
}

object DestinationsArgs {
    const val HOUSE_ID_ARG = "houseId"
}

object Destinations {
    const val HOUSES_ROUTE = HOUSES_SCREEN
    const val HOUSE_DETAIL_ROUTE = "$HOUSE_DETAIL_SCREEN/{$HOUSE_ID_ARG}"
}

class NavigationActions(private val navController: NavHostController) {

    // In case we need to navigate to root
    fun navigateToHouses() {
        navController.navigate(
            HOUSES_SCREEN
        ) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToHouseDetail(id: Int) {
        navController.navigate("$HOUSE_DETAIL_SCREEN/$id")
    }
}

