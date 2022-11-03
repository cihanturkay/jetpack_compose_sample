package com.sample.got.houses

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HousesScreen(
    onHouseClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    Scaffold(
        topBar = { HouseListTopBar() },
        scaffoldState = scaffoldState,
        modifier = modifier.fillMaxWidth()
    ) { paddingValues ->

        Button(onClick = { onHouseClick(1) }) {
            Text(text = "Go to House detail")
        }

    }
}