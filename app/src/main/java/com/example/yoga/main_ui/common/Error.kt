package com.example.yoga.main_ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.yoga.R
import com.example.yoga.main_ui.theme.YogaTheme

private const val ERROR_ICON_CONTENT_DESCRIPTION = "error icon"
private const val IMAGE_SIZE = 100
private const val TEXT_PADDING = 10

@Composable
fun Error(
    modifier: Modifier = Modifier, message: String
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(size = IMAGE_SIZE.dp),
            painter = painterResource(id = R.drawable.om),
            contentDescription = ERROR_ICON_CONTENT_DESCRIPTION,
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.secondary),
        )
        Text(
            modifier = Modifier.padding(all = TEXT_PADDING.dp),
            text = message,
            textAlign = TextAlign.Center
        )
    }
}

@PreviewLightDark
@Composable
fun ErrorPreview() {
    YogaTheme {
        Surface {
            Error(message = "error message")
        }
    }
}
