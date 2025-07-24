package com.example.yoga.main_ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.example.yoga.main_ui.theme.YogaTheme

private const val ICON_BUTTON_CONTENT_DESCRIPTION = "Navigate Back"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    screenName: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit = {},
) {
    TopAppBar(
        title = {
            Text(text = screenName)
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        navigationIcon = {
            if (canNavigateBack) {
                NavigationIcon(onClick = navigateUp)
            }
        },
    )
}

@Composable
private fun NavigationIcon(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = ICON_BUTTON_CONTENT_DESCRIPTION
        )
    }
}

@Composable
@Preview(
    name = "Can Navigate Back - Dark",
    uiMode = UI_MODE_NIGHT_YES
)
@Preview(
    name = "Can Navigate Back - Normal",
    uiMode = UI_MODE_TYPE_NORMAL
)
fun TopBarNavigationBackPreview() {
    YogaTheme {
        Surface {
            TopBar(
                screenName = "Yoga",
                canNavigateBack = true,
            )
        }
    }
}

@Composable
@Preview(
    name = "Cannot Navigate Back - Dark",
    uiMode = UI_MODE_NIGHT_YES
)
@Preview(
    name = "Cannot Navigate Back - Normal",
    uiMode = UI_MODE_TYPE_NORMAL
)
fun TopBarNoNavigationBackPreview() {
    YogaTheme {
        Surface {
            TopBar(
                screenName = "Yoga",
                canNavigateBack = false,
            )
        }
    }
}
