package com.sample.got.housedetail

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.sample.got.R
import com.sample.got.ui.theme.GOTTheme

@Composable
fun HouseDetailBar(
    title: String = stringResource(id = R.string.house_detail_top_app_bar_title),
    onBack: () -> Unit
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(Icons.Filled.ArrowBack, stringResource(id = R.string.menu_back))
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview
@Composable
private fun HouseDetailAppBarPreview() {
    GOTTheme {
        Surface {
            HouseDetailBar { }
        }
    }
}
