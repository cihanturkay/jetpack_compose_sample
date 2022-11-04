package com.sample.got.houses

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun HousesScreen(
    onHouseClick: (Int) -> Unit,
    viewModel: HousesViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    Scaffold(
        topBar = { HouseListTopBar() },
        scaffoldState = scaffoldState,
        modifier = modifier.fillMaxWidth()
    ) { paddingValues ->

        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        Log.d(
            "AAA",
            " Loading " + uiState.isLoading + " Error" + uiState.isError + " Data Size " + uiState.items.size
        )

        Button(onClick = { onHouseClick(1) }) {
            Text(text = "Go to House detail")
        }

    }
}