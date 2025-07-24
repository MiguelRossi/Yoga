package com.example.yoga.asana_detail.ui

import androidx.activity.compose.LocalActivity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.yoga.asana_detail.presentation.DetailUiState.Error
import com.example.yoga.asana_detail.presentation.DetailUiState.Loading
import com.example.yoga.asana_detail.presentation.DetailUiState.Success
import com.example.yoga.asana_detail.presentation.DetailViewModel
import com.example.yoga.domain.model.Asana
import com.example.yoga.main_ui.common.Error
import com.example.yoga.main_ui.common.Loading

@Composable
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    widthSizeClass: WindowWidthSizeClass =
        calculateWindowSizeClass(activity = LocalActivity.current!!).widthSizeClass,
    modifier: Modifier,
    poseId: Int
) {
    val state by viewModel.pose.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = Unit) {
        viewModel.onScreenStarted(poseId)
    }

    when (state) {
        is Error -> Error(
            modifier = modifier,
            message = (state as Error).message
        )

        is Success -> Detail(
            modifier = modifier,
            widthSizeClass = widthSizeClass,
            asana = (state as Success).asana
        )

        Loading -> Loading(modifier)
    }
}

@Composable
private fun Detail(
    modifier: Modifier,
    asana: Asana,
    widthSizeClass: WindowWidthSizeClass
) {
    if (widthSizeClass == WindowWidthSizeClass.Compact) {
        DetailCompact(
            modifier = modifier,
            asana = asana,
        )
    } else {
        DetailRegular(
            modifier = modifier,
            asana = asana,
        )
    }
}
