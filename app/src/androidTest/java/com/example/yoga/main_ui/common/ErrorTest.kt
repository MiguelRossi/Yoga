package com.example.yoga.main_ui.common

import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.accessibility.enableAccessibilityChecks
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import androidx.compose.ui.test.tryPerformAccessibilityChecks
import org.junit.Before
import org.junit.Rule
import org.junit.Test

private const val ERROR_MESSAGE = "Error message"

class ErrorTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            Error(
                modifier = Modifier,
                message = ERROR_MESSAGE
            )
        }
    }

    @Test
    fun givenScreenStarts_thenErrorIconShown() {
        composeTestRule
            .onRoot(useUnmergedTree = true)
            .printToLog(tag = "semanticTree")

        composeTestRule
            .onAllNodes(
                matcher = SemanticsMatcher.expectValue(
                    key = SemanticsProperties.Role,
                    expectedValue = Role.Image
                )
            ).assertCountEquals(1)
    }

    @Test
    fun givenScreenStarts_thenErrorMessageShown() {
        composeTestRule
            .onNodeWithText(text = ERROR_MESSAGE)
            .assertIsDisplayed()
    }

    @Test
    fun test_accessibility() {
        composeTestRule.enableAccessibilityChecks()

        composeTestRule.onRoot().tryPerformAccessibilityChecks()
    }
}
