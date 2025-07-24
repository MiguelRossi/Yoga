package com.example.yoga.asanas.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.yoga.asanas.presentation.AsanasUiState.Error
import com.example.yoga.asanas.presentation.AsanasUiState.Loading
import com.example.yoga.asanas.presentation.AsanasUiState.Success
import com.example.yoga.asanas.presentation.AsanasViewModel
import com.example.yoga.main_ui.common.Error
import com.example.yoga.main_ui.common.Loading

@Composable
fun AsanasScreen(
    modifier: Modifier,
    viewModel: AsanasViewModel = hiltViewModel(),
    onClickAsana: (Int, String) -> Unit
) {
    val state by viewModel.asanas.collectAsStateWithLifecycle()

    when (state) {
        Loading -> Loading(modifier)
        is Error -> Error(
            modifier = modifier,
            message = (state as Error).message
        )

        is Success -> Asanas(
            modifier = modifier,
            asanas = (state as Success).asanas,
            onClickAsana = onClickAsana
        )
    }
}
