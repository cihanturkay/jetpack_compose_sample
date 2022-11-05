package com.sample.got.houses

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.sample.got.data.model.House
import kotlinx.coroutines.flow.Flow

@Composable
fun HousesScreen(
    onHouseClick: (House) -> Unit,
    viewModel: HousesViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    Scaffold(
        topBar = { HouseListTopBar() },
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxWidth()
    ) { paddingValues ->
        HouseList(houses = viewModel.houses, onHouseClick)
    }
}

@Composable
fun HouseList(
    houses: Flow<PagingData<House>>,
    onHouseClick: (House) -> Unit,
) {
    val lazyHouseItems = houses.collectAsLazyPagingItems()
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        content = {
            items(lazyHouseItems.itemCount) { index ->
                lazyHouseItems[index]?.let {
                    HouseItem(house = it, onHouseClicked = onHouseClick)
                }
            }

            lazyHouseItems.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        //You can add modifier to manage load state when first time response page is loading
                    }
                    loadState.append is LoadState.Loading -> {
                        //You can add modifier to manage load state when next response page is loading
                    }
                    loadState.append is LoadState.Error -> {
                        //You can use modifier to show error message
                    }
                }
            }
        }
    )
}