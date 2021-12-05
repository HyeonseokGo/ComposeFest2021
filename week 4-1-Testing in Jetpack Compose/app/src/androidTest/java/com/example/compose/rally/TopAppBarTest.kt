package com.example.compose.rally

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.text.toUpperCase
import com.example.compose.rally.ui.components.RallyTopAppBar
import org.junit.Rule
import org.junit.Test
import java.util.*

class TopAppBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun rallyTopAppBarTest() {
        val allScreens = RallyScreen.values().toList()
        composeTestRule.setContent {
            RallyTopAppBar(
                allScreens = allScreens,
                onTabSelected = { /*TODO*/ },
                currentScreen = RallyScreen.Accounts
            )
        }

        composeTestRule
            .onNode(
                hasText(RallyScreen.Accounts.name.uppercase()) and
                        hasParent(
                            hasContentDescription(RallyScreen.Accounts.name)
                        ),
                useUnmergedTree = true
            )
            .assertExists()
    }

    @Test
    fun test() {
        val selectedTab = mutableStateOf(RallyScreen.Overview)
        val allScreens = RallyScreen.values().toList()

        composeTestRule.setContent {
            RallyTopAppBar(
                allScreens = allScreens,
                onTabSelected = {
                    selectedTab.value = it
                },
                currentScreen = selectedTab.value
            )
        }

        composeTestRule.onNodeWithContentDescription(RallyScreen.Accounts.name).performClick()

        assert(selectedTab.value == RallyScreen.Accounts)

        composeTestRule.onNodeWithContentDescription(RallyScreen.Accounts.name).assertIsSelected()

    }

}