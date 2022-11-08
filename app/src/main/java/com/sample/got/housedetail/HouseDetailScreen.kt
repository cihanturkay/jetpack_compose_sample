package com.sample.got.housedetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.flowlayout.FlowRow
import com.sample.got.R
import com.sample.got.data.model.Character
import com.sample.got.data.model.House
import com.sample.got.data.model.getId
import com.sample.got.util.FilledChipText
import com.sample.got.util.ProgressBar
import com.sample.got.util.Retry

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun HouseDetailScreen(
    onBack: () -> Unit,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    viewModel: HouseDetailViewModel = hiltViewModel(),
) {
    Scaffold(
        topBar = { HouseDetailBar(onBack = onBack) },
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->

        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        if (uiState.isLoading) {
            ProgressBar()
        } else if (uiState.isError) {
            Retry(onRetry = { viewModel.load() }, message = "Failed to load house details")
        } else {
            uiState.house?.let { house ->
                HouseDetailContent(house, uiState.characters)
            }
        }
    }
}

@Composable
fun HouseDetailContent(house: House, characters: List<Character>) {
    //TODO move hardcoded strings to resources
    Column {
        Text(
            text = house.name,
            style = MaterialTheme.typography.h6,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(8.dp)
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item {
                Column(modifier = Modifier.padding(dimensionResource(id = R.dimen.horizontal_margin))) {
                    FilledText(house.region, "Region")
                    TextArray(values = house.titles, label = "Titles")
                    FilledText(house.words, "Words")
                    TextArray(values = house.seats, label = "Seats")
                    TextArray(
                        values = house.ancestralWeapons,
                        label = "Ancestral Weapons"
                    )
                }
            }

            if (characters.isNotEmpty()) {
                item {
                    Text(
                        text = "Sworn Members (${characters.size})",
                        style = MaterialTheme.typography.subtitle1,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.LightGray)
                            .padding(12.dp)
                    )
                }

                items(
                    items = characters,
                    key = { character -> "C" + character.getId() },
                    itemContent = { character ->
                        CharacterItem(character = character)
                    })
            }
        }
    }
}

@Composable
fun CharacterItem(character: Character) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column(modifier = Modifier.padding(dimensionResource(id = R.dimen.list_item_padding))) {
            Text(character.name, fontWeight = FontWeight.Bold)
            FlowRow(
                mainAxisSpacing = dimensionResource(id = R.dimen.list_item_padding),
                crossAxisSpacing = dimensionResource(id = R.dimen.list_item_padding),
            ) {
                FilledChipText(character.gender, "Gender")
                FilledChipText(character.culture, "Culture")
                FilledChipText(character.born, "Born")
            }
        }
        Divider(color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f))
    }
}

@Composable
fun TextArray(values: List<String>, label: String) {
    val text = values.joinToString(separator = ", ") { it }
    if (text.isNotEmpty()) {
        Text(
            text = "${label}: $text",
            style = MaterialTheme.typography.body1
        )
    }
}

@Composable
fun FilledText(value: String, label: String) {
    if (value.isNotEmpty()) {
        Text(
            text = "${label}: $value",
            style = MaterialTheme.typography.body1
        )
    }
}