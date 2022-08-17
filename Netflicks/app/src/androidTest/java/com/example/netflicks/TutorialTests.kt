package com.example.netflicks

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.netflicks.ui.theme.NetflicksTheme
import org.junit.Rule
import org.junit.Test

class TutorialTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun tutorialDescriptionTest() {
        val textList = arrayListOf<String>()

        composeTestRule.setContent {
            NetflicksTheme {
                TutorialScreen {}
                for (page in pages) {
                    val text = "${stringResource(id = page)} " +
                            "${stringResource(id = R.string.tutorial_the_best)} " +
                            stringResource(id = R.string.tutorial_text_prefix)
                    textList.add(text)
                }
            }
        }

        composeTestRule.onRoot().printToLog("TutorialTest")

        composeTestRule.onNodeWithText("NEXT").assertIsDisplayed()

        composeTestRule.onNodeWithText(textList[0]).assertIsDisplayed()
        composeTestRule.onNodeWithText("NEXT").performClick()

        composeTestRule.onNodeWithText(textList[1]).assertIsDisplayed()
        composeTestRule.onNodeWithText("NEXT").performClick()

        composeTestRule.onNodeWithText(textList[2]).assertIsDisplayed()
        composeTestRule.onNodeWithText("NEXT").performClick()

        composeTestRule.onNodeWithText(textList[3]).assertIsDisplayed()
    }
}
