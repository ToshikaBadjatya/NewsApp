package com.example.newsapp

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.performTextInput
import androidx.loader.content.Loader
import androidx.paging.LoadState
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.newsapp.ui.UIState
import com.example.newsapp.ui.commonUi.NewsUiList
import com.example.newsapp.ui.commonUi.ShowLoading
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
    fun checkIfSearchBarIsVisibleAfterEnteringText(){
        composeTestRule.setContent {
            Search("") { }
        }
        composeTestRule.onNodeWithContentDescription(TestingSemantics.SEARCH_BAR)
            .assertExists()
            .performTextInput("search")


        composeTestRule.onNodeWithContentDescription(TestingSemantics.SEARCH_BAR)
            .assertExists()

    }
    @Test
    fun checkIfSearchBarIsVisibleAfterSearchListPopulate(){
            composeTestRule.setContent {
                Column {
                    Search("someText") {}
                    NewsUiList(UIState.Success(TestData.articleList))
                }
            }
        composeTestRule.onNodeWithContentDescription(TestingSemantics.NEWS_LIST, useUnmergedTree = true)
            .assertExists()
            .performScrollToNode(hasText(TestData.articleList[0].title?:"",true))

            composeTestRule.onNodeWithContentDescription(TestingSemantics.SEARCH_BAR, useUnmergedTree = true)
                .assertExists()




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