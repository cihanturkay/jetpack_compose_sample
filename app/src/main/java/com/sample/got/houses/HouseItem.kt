package com.sample.got.houses

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.sample.got.R
import com.sample.got.data.model.House
import com.sample.got.ui.theme.GOTTheme
import com.sample.got.util.FilledChipText

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HouseItem(house: House, onHouseClicked: (House) -> Unit) {
    Card(
        shape = MaterialTheme.shapes.large,
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = contentColorFor(backgroundColor),
        elevation = 2.dp,
        border = BorderStroke(1.dp, MaterialTheme.colors.onSurface.copy(alpha = 0.1f)),
        onClick = { onHouseClicked(house) },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = dimensionResource(id = R.dimen.list_item_padding),
                    vertical = dimensionResource(id = R.dimen.list_item_padding),
                )
        ) {
            Text(
                text = house.name,
                style = MaterialTheme.typography.h6,
            )
            Spacer(modifier = Modifier.height(8.dp))
            FlowRow(
                mainAxisSpacing = dimensionResource(id = R.dimen.list_item_padding),
                crossAxisSpacing = dimensionResource(id = R.dimen.list_item_padding),
            ) {
                FilledChipText(house.words, "Words")
                FilledChipText(house.region, "Region")
                FilledChipText(house.swornMembers.size.toString(), "Members")
            }
        }
    }
}

@Preview
@Composable
fun HouseItemPreview() {
    GOTTheme {
        Surface {
            HouseItem(
                house = House(
                    url = "",
                    coatOfArms = "",
                    titles = listOf(),
                    seats = listOf(),
                    currentLord = "",
                    heir = "",
                    overlord = "",
                    founded = "",
                    founder = "",
                    diedOut = "",
                    ancestralWeapons = listOf(),
                    cadetBranches = listOf(),
                    name = "House Targaryen of King's Landing",
                    region = "The Crownlands",
                    words = "Fire and Blood",
                    swornMembers = arrayListOf(
                        "https://anapioficeandfire.com/api/characters/33",
                        "https://anapioficeandfire.com/api/characters/42",
                        "https://anapioficeandfire.com/api/characters/43"
                    ),
                ), onHouseClicked = {}
            )
        }
    }
}