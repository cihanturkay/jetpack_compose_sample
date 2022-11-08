package com.sample.got.houses

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.sample.got.data.model.House
import com.sample.got.data.model.getId
import com.sample.got.util.ProgressBar
import com.sample.got.util.Retry

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
            onHouseClick = onHouseClick,
            viewModel = viewModel,
        )
    }
}

@Composable
fun HouseList(
    onHouseClick: (House) -> Unit,
    viewModel: HousesViewModel
) {
    val lazyHouseItems = viewModel.houses.collectAsLazyPagingItems()
    lazyHouseItems.apply {
        when (loadState.refresh) {
            is LoadState.Loading -> ProgressBar()
            is LoadState.Error -> {
                Retry(
                    onRetry = { lazyHouseItems.refresh() },
                    message = "Something went wrong while loading houses"
                )
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