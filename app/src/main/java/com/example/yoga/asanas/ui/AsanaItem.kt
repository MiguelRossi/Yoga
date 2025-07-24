package com.example.yoga.asanas.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.yoga.domain.model.Asana
import com.example.yoga.main_ui.theme.YogaTheme
import com.example.yoga.main_ui.tools.getNightModeFriendlyPlaceholder

private const val PADDING = 20
private const val IMAGE_SIDE = 50
private const val NAME_MIN_LINES = 2

@Composable
fun AsanaItem(
    asana: Asana,
    onClickAsana: (Int, String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxHeight()
            .clickable(onClick = { onClickAsana(asana.id, asana.englishName) }),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = PADDING.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(size = IMAGE_SIDE.dp),
                model = asana.urlSvg,
                placeholder = getNightModeFriendlyPlaceholder(),
                error = getNightModeFriendlyPlaceholder(),
                contentDescription = null
            )

            Text(
                modifier = Modifier
                    .padding(top = PADDING.dp),
                text = asana.sanskritName,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                minLines = NAME_MIN_LINES
            )
        }
    }
}

@Composable
@PreviewLightDark
fun AsanaItemPreview() {
    val asana = Asana(
        id = 1,
        englishName = "englishName",
        sanskritNameAdapted = "sanskritNameAdapted",
        sanskritName = "sanskritName",
        translationName = "translationName",
        poseDescription = "poseDescription",
        poseBenefits = "poseBenefits",
        urlSvg = "urlSvg"
    )
    YogaTheme {
        Surface {
            AsanaItem(asana) { _, _ -> }
        }
    }
}
