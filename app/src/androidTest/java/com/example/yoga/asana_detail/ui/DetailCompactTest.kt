package com.example.yoga.asana_detail.ui

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import coil3.ColorImage
import coil3.EventListener
import coil3.ImageLoader
import coil3.SingletonImageLoader
import coil3.annotation.DelicateCoilApi
import coil3.request.ErrorResult
import coil3.request.ImageRequest
import coil3.request.SuccessResult
import coil3.test.FakeImageLoaderEngine
import com.example.yoga.domain.model.Asana
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

private const val TEST_TAG = "detail compact test tag"

class DetailCompactTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val fakeAsana = Asana(
        id = 1,
        englishName = "englishName",
        sanskritNameAdapted = "sanskritNameAdapted",
        sanskritName = "sanskritName",
        translationName = "translationName",
        poseDescription = "poseDescription",
        poseBenefits = "poseBenefits",
        urlSvg = "urlSvg"
    )

    private val coilFakeEngine = FakeImageLoaderEngine.Builder()
        .intercept(data = fakeAsana.urlSvg, image = ColorImage(Color.Red.toArgb()))
        .default(image = ColorImage(Color.Blue.toArgb()))
        .build()

    @Test
    fun givenAsana_whenDetailScreenIsDisplayed_thenScreenPopulated() {
        composeTestRule.setContent { DetailCompact(modifier = Modifier, asana = fakeAsana) }

        composeTestRule.onNode(
            matcher = hasText(text = fakeAsana.englishName) and hasTestTag(testTag = TEST_TAG)
        ).assertIsDisplayed()
        composeTestRule.onNode(
            matcher = hasText(text = fakeAsana.sanskritNameAdapted) and hasTestTag(testTag = TEST_TAG)
        ).assertIsDisplayed()
        composeTestRule.onNode(
            matcher = hasText(text = fakeAsana.sanskritName) and hasTestTag(testTag = TEST_TAG)
        ).assertIsDisplayed()
        composeTestRule.onNode(
            matcher = hasText(text = fakeAsana.translationName) and hasTestTag(testTag = TEST_TAG)
        ).assertIsDisplayed()
        composeTestRule.onNode(
            matcher = hasText(text = fakeAsana.poseDescription) and hasTestTag(testTag = TEST_TAG)
        ).assertIsDisplayed()
        composeTestRule.onNode(
            matcher = hasText(text = fakeAsana.poseBenefits) and hasTestTag(testTag = TEST_TAG)
        ).assertIsDisplayed()
    }

    @Test
    @OptIn(DelicateCoilApi::class)
    fun givenAsana_whenDetailScreenIsDisplayed_thenImageIsLoaded() {
        var loaded = false

        composeTestRule.setContent {
            val imageLoader = ImageLoader.Builder(context = LocalContext.current)
                .components { add(coilFakeEngine) }
                .eventListener(listener = object : EventListener() {
                    override fun onSuccess(request: ImageRequest, result: SuccessResult) {
                        loaded = true
                    }

                    override fun onError(request: ImageRequest, result: ErrorResult) {
                        loaded = false
                    }
                })
                .build()
            SingletonImageLoader.setUnsafe(imageLoader)
            DetailCompact(modifier = Modifier, asana = fakeAsana)
        }

        assertTrue(loaded)
    }
}
