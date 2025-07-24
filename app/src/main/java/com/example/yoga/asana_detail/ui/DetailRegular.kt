package com.example.yoga.asana_detail.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.yoga.R
import com.example.yoga.domain.model.Asana
import com.example.yoga.main_ui.theme.YogaTheme
import com.example.yoga.main_ui.tools.getNightModeFriendlyPlaceholder

private const val TEST_TAG = "detail regular test tag"
private const val SMALL_PADDING = 10
private const val BIG_PADDING = 20
private const val IMAGE_PADDING = 20
private const val COLUMN_PADDING = 2
private const val COLUMN_WEIGHT = 1F

private const val DIVIDER_PADDING = 8
private const val DIVIDER_ALPHA = .3F

private const val FOOTER_ICON_DESCRIPTION = "footer icon"
private const val FOOTER_PADDING = 60
private const val FOOTER_SIZE = 40
private const val SPACER_WEIGHT = 1F

@Composable
fun DetailRegular(
    modifier: Modifier,
    asana: Asana
) {
    val dividerColor = MaterialTheme.colorScheme.secondary.copy(alpha = DIVIDER_ALPHA)

    Row(
        modifier = modifier
            .padding(all = BIG_PADDING.dp)
            .verticalScroll(state = rememberScrollState())
            .fillMaxSize()
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(
            modifier = Modifier
                .weight(weight = COLUMN_WEIGHT)
                .padding(end = COLUMN_PADDING.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = BIG_PADDING.dp,
                        bottom = SMALL_PADDING.dp
                    ),
                text = stringResource(id = R.string.sanskrit),
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(tag = TEST_TAG),
                text = asana.sanskritName
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(tag = TEST_TAG),
                text = asana.sanskritNameAdapted
            )
            HorizontalDivider(
                modifier = Modifier
                    .padding(top = DIVIDER_PADDING.dp),
                color = dividerColor
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = BIG_PADDING.dp,
                        bottom = SMALL_PADDING.dp
                    ),
                text = stringResource(id = R.string.english),
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(tag = TEST_TAG),
                text = asana.englishName
            )
            HorizontalDivider(
                modifier = Modifier.padding(top = DIVIDER_PADDING.dp),
                color = dividerColor
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = BIG_PADDING.dp,
                        bottom = SMALL_PADDING.dp
                    ),
                text = stringResource(id = R.string.translation),
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(tag = TEST_TAG),
                text = asana.translationName
            )
            HorizontalDivider(
                modifier = Modifier.padding(top = DIVIDER_PADDING.dp),
                color = dividerColor
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = BIG_PADDING.dp,
                        bottom = SMALL_PADDING.dp
                    ),
                text = stringResource(id = R.string.benefits),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = asana.poseBenefits,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(tag = TEST_TAG),
                textAlign = TextAlign.Justify
            )
            HorizontalDivider(
                modifier = Modifier.padding(top = DIVIDER_PADDING.dp),
                color = dividerColor
            )
            Spacer(modifier = Modifier.weight(weight = SPACER_WEIGHT))
            Image(
                modifier = Modifier
                    .padding(vertical = FOOTER_PADDING.dp)
                    .size(size = FOOTER_SIZE.dp),
                painter = painterResource(id = R.drawable.om),
                contentDescription = FOOTER_ICON_DESCRIPTION,
                colorFilter = ColorFilter
                    .tint(color = MaterialTheme.colorScheme.secondary),
            )
        }
        VerticalDivider(
            modifier = Modifier
                .fillMaxHeight()
                .padding(all = DIVIDER_PADDING.dp),
        )
        Column(
            modifier = Modifier
                .weight(weight = COLUMN_WEIGHT)
                .padding(start = COLUMN_PADDING.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = BIG_PADDING.dp,
                        bottom = SMALL_PADDING.dp
                    ),
                text = stringResource(id = R.string.description),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = asana.poseDescription,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(tag = TEST_TAG),
                textAlign = TextAlign.Justify
            )
            HorizontalDivider(
                modifier = Modifier.padding(top = DIVIDER_PADDING.dp),
                color = dividerColor
            )
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = IMAGE_PADDING.dp),
                model = asana.urlSvg,
                placeholder = getNightModeFriendlyPlaceholder(),
                contentDescription = null
            )
        }
    }
}

@Composable
@PreviewLightDark
fun DetailRegularPreview() {
    YogaTheme {
        Surface {
            DetailRegular(
                modifier = Modifier,
                asana = Asana(
                    id = 1,
                    englishName = "englishName",
                    sanskritNameAdapted = "sanskritNameAdapted",
                    sanskritName = "sanskritName",
                    translationName = "translationName",
                    poseDescription = "poseDescription",
                    poseBenefits = "poseBenefits",
                    urlSvg = "urlSvg"
                )
            )
        }
    }
}
