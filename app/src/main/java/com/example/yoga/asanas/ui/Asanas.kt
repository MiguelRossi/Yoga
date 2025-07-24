package com.example.yoga.asanas.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.yoga.domain.model.Asana

private const val NUMBER_OF_COLUMNS = 2
private const val PADDING_VALUES = 10

@Composable
fun Asanas(
    modifier: Modifier,
    asanas: List<Asana>,
    onClickAsana: (Int, String) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize(),
        columns = GridCells.Fixed(count = NUMBER_OF_COLUMNS),
        contentPadding = PaddingValues(all = PADDING_VALUES.dp),
        verticalArrangement = Arrangement.spacedBy(space = PADDING_VALUES.dp),
        horizontalArrangement = Arrangement.spacedBy(space = PADDING_VALUES.dp),
    ) {
        items(items = asanas) { asana ->
            AsanaItem(asana, onClickAsana)
        }
    }
}
