package com.example.yoga.main_ui.common

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.accessibility.enableAccessibilityChecks
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.tryPerformAccessibilityChecks
import org.junit.Before
import org.junit.Rule
import org.junit.Test

private const val TEST_TAG = "loading test tag"

class LoadingTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            Loading(modifier = Modifier)
        }
    }

    @Test
    fun givenScreenStarts_thenShowCircularProgressIndicator() {
        composeTestRule
            .onNodeWithTag(testTag = TEST_TAG)
            .assertIsDisplayed()
    }

    @Test
    fun test_accessibility() {
        composeTestRule.enableAccessibilityChecks()

        composeTestRule.onRoot().tryPerformAccessibilityChecks()
    }
}
