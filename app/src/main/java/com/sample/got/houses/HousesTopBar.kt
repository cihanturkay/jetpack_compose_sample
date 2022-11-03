package com.sample.got.houses

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.sample.got.R

@Preview
@Composable
fun HouseListTopBar() {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.house_list_top_app_bar_title)) },
        modifier = Modifier.fillMaxWidth()
    )
}


