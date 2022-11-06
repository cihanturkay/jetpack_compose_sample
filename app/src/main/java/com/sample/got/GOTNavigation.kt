package com.sample.got

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.sample.got.GOTDestinationsArgs.HOUSE_ID_ARG
import com.sample.got.GOTScreens.GOT_HOUSES_SCREEN
import com.sample.got.GOTScreens.GOT_HOUSE_DETAIL_SCREEN

private object GOTScreens {
    const val GOT_HOUSES_SCREEN = "houses"
    const val GOT_HOUSE_DETAIL_SCREEN = "houseDetail"
}

object GOTDestinationsArgs {
    const val HOUSE_ID_ARG = "houseId"
}

object GOTDestinations {
    const val HOUSES_ROUTE = GOT_HOUSES_SCREEN
    const val HOUSE_DETAIL_ROUTE = "$GOT_HOUSE_DETAIL_SCREEN/{$HOUSE_ID_ARG}"
}

class GOTNavigationActions(private val navController: NavHostController) {

    // In case we need to navigate to root
    fun navigateToHouses() {
        navController.navigate(
            GOT_HOUSES_SCREEN
        ) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToHouseDetail(id: Int) {
        navController.navigate("$GOT_HOUSE_DETAIL_SCREEN/$id")
    }
}

