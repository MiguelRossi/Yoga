package com.example.yoga.main_ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.example.yoga.main_ui.theme.YogaTheme

private const val TEST_TAG = "loading test tag"

@Composable
fun Loading(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            modifier = Modifier.testTag(tag = TEST_TAG)
        )
    }
}

@Composable
@PreviewLightDark
fun LoadingPreview() {
    YogaTheme {
        Surface {
            Loading()
        }
    }
}
