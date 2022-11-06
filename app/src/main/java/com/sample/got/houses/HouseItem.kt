package com.sample.got.houses

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.sample.got.R
import com.sample.got.data.model.House
import com.sample.got.ui.theme.GOTTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HouseItem(house: House, onHouseClicked: (House) -> Unit) {
    Card(
        shape = MaterialTheme.shapes.large,
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = contentColorFor(backgroundColor),
        elevation = 4.dp,
        border = BorderStroke(1.dp, MaterialTheme.colors.onSurface),
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
                mainAxisSpacing = dimensionResource(id = R.dimen.horizontal_margin),
                crossAxisSpacing = dimensionResource(id = R.dimen.horizontal_margin) / 2,
            ) {
                HouseItemChip(null, house.words)
                HouseItemChip(Icons.Filled.LocationOn, house.region)
                HouseItemChip(Icons.Filled.AccountCircle, house.swornMembers.size.toString())
            }
        }
    }

}

@Composable
fun HouseItemChip(icon: ImageVector?, text: String) {
    if (text.isNotEmpty()) {
        Box(
            modifier = Modifier
                .border(BorderStroke(1.dp, Color.Gray), shape = RoundedCornerShape(10.dp))
                .padding(8.dp, 4.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                icon?.let {
                    Icon(it, contentDescription = null, Modifier.size(20.dp))
                }
                Text(
                    text = text,
                    style = MaterialTheme.typography.body1,
                )
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