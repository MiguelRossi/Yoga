package com.example.yoga.asanas.ui

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.accessibility.enableAccessibilityChecks
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.tryPerformAccessibilityChecks
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

class AsanaItemTest {

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
    fun givenAsana_whenAsanaItemIsDisplayed_thenScreenPopulated() {
        composeTestRule.setContent { AsanaItem(asana = fakeAsana) { _, _ -> } }

        composeTestRule
            .onNodeWithText(text = fakeAsana.sanskritName)
            .assertIsDisplayed()
    }

    @Test
    @OptIn(DelicateCoilApi::class)
    fun givenAsana_whenAsanaItemIsDisplayed_thenImageIsLoaded() {
        composeTestRule.setContent {
            val imageLoader = ImageLoader.Builder(context = LocalContext.current)
                .components { add(coilFakeEngine) }
                .eventListener(listener = object : EventListener() {
                    override fun onSuccess(
                        request: ImageRequest,
                        result: SuccessResult
                    ) = Unit

                    override fun onError(
                        request: ImageRequest,
                        result: ErrorResult
                    ) {
                        throw Exception("Image loading failed")
                    }
                })
                .build()
            SingletonImageLoader.setUnsafe(imageLoader)
            AsanaItem(asana = fakeAsana) { _, _ -> }
        }
        TODO(reason = "this doesn't fail")
    }

    @Test
    fun givenAsana_whenAsanaItemIsDisplayed_andItemClicked_thenOnclickCalled() {
        var clicked = false
        composeTestRule.setContent { AsanaItem(asana = fakeAsana) { _, _ -> clicked = true } }

        composeTestRule.onRoot().performClick()

        assertTrue(clicked)
    }

    @Test
    fun test_accessibility() {
        composeTestRule.enableAccessibilityChecks()

        composeTestRule.onRoot().tryPerformAccessibilityChecks()
    }
}
