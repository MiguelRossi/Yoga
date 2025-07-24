package com.example.yoga.main_ui

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.accessibility.enableAccessibilityChecks
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.tryPerformAccessibilityChecks
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

private const val ICON_BUTTON_CONTENT_DESCRIPTION = "Navigate Back"

class TopBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun givenScreenName_andCanNavigateBackFalse_whenTopBarIsDisplayed_thenScreenNameIsDisplayed() {
        val screenName = "screen_name"
        composeTestRule.setContent {
            TopBar(
                screenName = screenName, canNavigateBack = false
            )
        }

        composeTestRule
            .onNodeWithText(text = screenName)
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithContentDescription(label = ICON_BUTTON_CONTENT_DESCRIPTION)
            .assertIsNotDisplayed()
    }

    @Test
    fun givenScreenName_andCanNavigateBackTrue_whenTopBarIsDisplayed_thenScreenName_andNavigateBackIconAreDisplayed() {
        val screenName = "screen_name"
        composeTestRule.setContent {
            TopBar(
                screenName = screenName,
                canNavigateBack = true,
            )
        }

        composeTestRule
            .onNodeWithText(text = screenName)
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithContentDescription(label = ICON_BUTTON_CONTENT_DESCRIPTION)
            .assertIsDisplayed()
    }

    @Test
    fun givenCanNavigateBackTrue_andNavigateUp_whenButtonIsPressed_thenLambdaCalled() {
        var wasCalled = false
        composeTestRule.setContent {
            TopBar(
                screenName = "screen name",
                canNavigateBack = true,
                navigateUp = { wasCalled = true }
            )
        }

        composeTestRule
            .onNodeWithContentDescription(label = ICON_BUTTON_CONTENT_DESCRIPTION)
            .assertIsEnabled()
        composeTestRule
            .onNodeWithContentDescription(label = ICON_BUTTON_CONTENT_DESCRIPTION)
            .assertHasClickAction()
        composeTestRule
            .onNodeWithContentDescription(label = ICON_BUTTON_CONTENT_DESCRIPTION)
            .performClick()
        assertTrue(wasCalled)
    }

    @Test
    fun test_accessibility() {
        composeTestRule.enableAccessibilityChecks()

        composeTestRule.onRoot().tryPerformAccessibilityChecks()
    }
}
