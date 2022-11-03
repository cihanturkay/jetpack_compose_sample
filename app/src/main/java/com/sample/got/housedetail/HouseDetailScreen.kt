package com.sample.got.housedetail

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HouseDetailScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    Scaffold(
        topBar = { HouseDetailBar(onBack = onBack) },
        scaffoldState = scaffoldState,
        modifier = modifier.fillMaxWidth()
    ) { paddingValues ->

        Text(text = "House detail")
    }
}