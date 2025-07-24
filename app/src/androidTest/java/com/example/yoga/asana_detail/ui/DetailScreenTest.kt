package com.example.yoga.asana_detail.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.example.yoga.asana_detail.presentation.DetailUiState
import com.example.yoga.asana_detail.presentation.DetailViewModel
import com.example.yoga.domain.model.Asana
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.junit.Rule
import org.junit.Test

private const val LOADING_TEST_TAG = "loading test tag"
private const val DETAIL_TEST_TAG = "detail compact test tag"
private const val REGULAR_TEST_TAG = "detail regular test tag"

class DetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val viewModel = mockk<DetailViewModel>()

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

    @Test
    fun givenScreenRendered_whenNetworkCallLoading_thenShowLoadingScreen() {
        val controlledUiStateFlow = MutableStateFlow(value = DetailUiState.Loading)
        every { viewModel.pose } returns controlledUiStateFlow.asStateFlow()

        val poseId = -1
        justRun { viewModel.onScreenStarted(poseId) }

        composeTestRule.setContent {
            DetailScreen(
                modifier = Modifier,
                viewModel = viewModel,
                poseId = poseId
            )
        }

        composeTestRule
            .onNodeWithTag(testTag = LOADING_TEST_TAG)
            .assertIsDisplayed()
    }

    @Test
    fun givenScreenRendered_whenNetworkCallFails_thenShowErrorScreen() {
        val errorMessage = "error message"
        val controlledUiStateFlow = MutableStateFlow(value = DetailUiState.Error(errorMessage))
        every { viewModel.pose } returns controlledUiStateFlow.asStateFlow()

        val poseId = -1
        justRun { viewModel.onScreenStarted(poseId) }

        composeTestRule.setContent {
            DetailScreen(
                modifier = Modifier,
                viewModel = viewModel,
                poseId = poseId
            )
        }

        composeTestRule
            .onNodeWithText(text = errorMessage)
            .assertIsDisplayed()
    }

    @Test
    fun givenCompactScreen_whenPoseFetched_thenShowPose() {
        val controlledUiStateFlow = MutableStateFlow(value = DetailUiState.Success(fakeAsana))
        every { viewModel.pose } returns controlledUiStateFlow.asStateFlow()

        val poseId = -1
        justRun { viewModel.onScreenStarted(poseId) }

        composeTestRule.setContent {
            DetailScreen(
                modifier = Modifier,
                widthSizeClass = WindowWidthSizeClass.Compact,
                viewModel = viewModel,
                poseId = poseId
            )
        }

        composeTestRule
            .onNode(
                matcher = hasText(text = fakeAsana.sanskritName) and hasTestTag(testTag = DETAIL_TEST_TAG)
            ).assertIsDisplayed()
    }

    @Test
    fun givenRegularScreen_whenPoseFetched_thenShowPose() {
        val controlledUiStateFlow = MutableStateFlow(value = DetailUiState.Success(fakeAsana))
        every { viewModel.pose } returns controlledUiStateFlow.asStateFlow()

        val poseId = -1
        justRun { viewModel.onScreenStarted(poseId) }

        composeTestRule.setContent {
            DetailScreen(
                modifier = Modifier,
                widthSizeClass = WindowWidthSizeClass.Medium,
                viewModel = viewModel,
                poseId = poseId
            )
        }

        composeTestRule
            .onNode(
                matcher = hasText(text = fakeAsana.sanskritName) and hasTestTag(testTag = REGULAR_TEST_TAG)
            ).assertIsDisplayed()
    }
}
