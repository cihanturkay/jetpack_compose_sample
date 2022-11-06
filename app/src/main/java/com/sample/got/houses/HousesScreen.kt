package com.sample.got.houses

import android.widget.ProgressBar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.sample.got.data.model.House
import com.sample.got.data.model.getId
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Composable
fun HousesScreen(
    onHouseClick: (House) -> Unit,
    viewModel: HousesViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    Scaffold(
        topBar = { HouseListTopBar() },
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->

        HouseList(
            houses = viewModel.houses,
            onHouseClick,
            snackbarHostState = scaffoldState.snackbarHostState
        )

    }
}

@Composable
fun HouseList(
    houses: Flow<PagingData<House>>,
    onHouseClick: (House) -> Unit,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope = rememberCoroutineScope()
) {
    val lazyHouseItems = houses.collectAsLazyPagingItems()
    lazyHouseItems.apply {
        when (loadState.refresh) {
            is LoadState.Loading -> ProgressBar()
            is LoadState.Error -> {
                scope.launch {
                    snackbarHostState.showSnackbar("Something went wrong while loading houses")
                }
            }
            is LoadState.NotLoading -> {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    items(
                        items = lazyHouseItems,
                        key = { house -> house.getId() },
                        itemContent = { house ->
                            house?.let { it ->
                                HouseItem(
                                    house = it,
                                    onHouseClicked = { onHouseClick(it) })
                            }
                        })
                }
            }
        }
    }
}

@Composable
fun ProgressBar() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}