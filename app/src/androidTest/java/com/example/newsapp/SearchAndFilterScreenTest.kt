package com.example.newsapp

import androidx.activity.ComponentActivity
import androidx.compose.material3.SearchBar
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.newsapp.ui.UIState
import com.example.newsapp.ui.commonUi.NewsUiList
import com.example.newsapp.ui.screens.FilterItems
import com.example.newsapp.ui.screens.Search
import com.example.newsapp.ui.screens.SearchScreen
import com.example.newsapp.ui.screens.filterOptions
import com.example.newsapp.utils.constants.TestingSemantics
import com.example.newsapp.utils.others.CustomErrorClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchAndFilterScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    @Test
    fun checkIfSearchBarIsVisible(){
        composeTestRule.setContent {
            Search("empty") { }
        }
        composeTestRule.onNodeWithContentDescription(TestingSemantics.SEARCH_BAR)
            .assertExists()
            .assertTextEquals("empty")
            .assertIsEnabled()

    }
    @Test
    fun checkFilterItemsPresent(){
        composeTestRule.setContent {
            FilterItems()
        }
        filterOptions.forEach { filter ->
            composeTestRule.onNodeWithText(filter.displayName)
                .assertExists()
        }
    }
}