package com.example.yoga.asanas.ui

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import com.example.yoga.asanas.presentation.AsanasUiState
import com.example.yoga.asanas.presentation.AsanasViewModel
import com.example.yoga.domain.model.Asana
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

private const val TEST_TAG = "loading test tag"

class AsanasScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val viewModel = mockk<AsanasViewModel>()

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
        val controlledUiStateFlow = MutableStateFlow(value = AsanasUiState.Loading)
        every { viewModel.asanas } returns controlledUiStateFlow.asStateFlow()

        composeTestRule.setContent {
            AsanasScreen(
                modifier = Modifier,
                viewModel = viewModel
            ) { _, _ -> }
        }

        composeTestRule
            .onNodeWithTag(testTag = TEST_TAG)
            .assertIsDisplayed()
    }

    @Test
    fun givenScreenRendered_whenNetworkCallFails_thenShowErrorScreen() {
        val errorMessage = "error message"
        val controlledUiStateFlow = MutableStateFlow(value = AsanasUiState.Error(errorMessage))
        every { viewModel.asanas } returns controlledUiStateFlow.asStateFlow()

        composeTestRule.setContent {
            AsanasScreen(
                modifier = Modifier,
                viewModel = viewModel
            ) { _, _ -> }
        }

        composeTestRule
            .onRoot(useUnmergedTree = true)
            .printToLog(tag = "semanticTree")

        composeTestRule
            .onNodeWithText(text = errorMessage)
            .assertIsDisplayed()
    }

    @Test
    fun givenScreenRendered_whenAsanasFetched_andItemClicked_thenCallOnClickAsana() {
        val asanas = getAsanas()
        val controlledUiStateFlow = MutableStateFlow(value = AsanasUiState.Success(asanas))
        every { viewModel.asanas } returns controlledUiStateFlow.asStateFlow()

        var clickedTimes = 0

        composeTestRule.setContent {
            AsanasScreen(
                modifier = Modifier,
                viewModel = viewModel
            ) { _, _ -> ++clickedTimes }
        }

        composeTestRule
            .onNodeWithText(text = asanas[0].sanskritName)
            .performClick()

        composeTestRule
            .onNodeWithText(text = asanas[1].sanskritName)
            .performClick()

        assertEquals(2, clickedTimes)
    }

    @Test
    fun givenScreenRendered_whenAsanasFetched_thenShowAsanas() {
        val asanas = getAsanas()
        val controlledUiStateFlow = MutableStateFlow(value = AsanasUiState.Success(asanas))
        every { viewModel.asanas } returns controlledUiStateFlow.asStateFlow()

        composeTestRule.setContent {
            AsanasScreen(
                modifier = Modifier,
                viewModel = viewModel
            ) { _, _ -> }
        }

        composeTestRule
            .onNodeWithText(text = asanas[0].sanskritName)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(text = asanas[1].sanskritName)
            .assertIsDisplayed()
    }

    private fun getAsanas() = listOf(
        fakeAsana,
        fakeAsana.copy(
            urlSvg = "urlSvg_1",
            englishName = "englishName_1",
            sanskritName = "sanskritName_1",
        )
    )
}
