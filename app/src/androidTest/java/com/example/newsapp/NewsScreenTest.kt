package com.example.newsapp

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToNode
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.newsapp.ui.UIState
import com.example.newsapp.ui.commonUi.NewsPaginationList
import com.example.newsapp.ui.commonUi.NewsUiList
import com.example.newsapp.ui.screens.TopHeadlineScreen
import com.example.newsapp.utils.constants.TestingSemantics
import com.example.newsapp.utils.others.CustomErrorClass
import com.example.newsapp.utils.others.Utils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewsScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun checkLoadingShowingOnApiLoading(){
        composeTestRule.setContent {
            NewsUiList(UIState.Loading)
        }
        composeTestRule.onNodeWithContentDescription(TestingSemantics.LOADER)
            .assertExists()

    }
    @Test
    fun checkErrorPopUpShowingOnApiError(){
        composeTestRule.setContent {
            NewsUiList(UIState.Failure("custom_error"))
        }
        composeTestRule.onNodeWithContentDescription(TestingSemantics.ERROR_MESSAGE)
            .assertExists()
            .assertTextEquals("custom_error")

    }

    @Test
    fun checkErrorPopUpShowingOnNoInternet(){
        composeTestRule.setContent {
            NewsUiList(UIState.Failure(CustomErrorClass.NoInternet.msg))
        }
        composeTestRule.onNodeWithContentDescription(TestingSemantics.ERROR_MESSAGE)
            .assertExists()
            .assertTextEquals(CustomErrorClass.NoInternet.msg)

    }
    @Test
    fun checkItemsDisplayedOnSuccess(){
        composeTestRule.setContent {
            NewsUiList(UIState.Success(TestData.articleList))
        }
        composeTestRule.onNodeWithContentDescription(TestingSemantics.NEWS_LIST)
            .assertExists()
        //long list
        composeTestRule
            .onNodeWithText(
                TestData.articleList[0].title!!,
                substring = true
            )
            .assertExists()
            .assertHasClickAction()

        composeTestRule.onNodeWithContentDescription(TestingSemantics.NEWS_LIST)
            .performScrollToNode(
                hasText(TestData.articleWithLongTextList.last().title!!)
            )

    }
    @Test
    fun checkItemsDisplayedOnLongList(){
        composeTestRule.setContent {
            NewsUiList(UIState.Success(TestData.articleWithLongTextList))
        }
        composeTestRule.onNodeWithContentDescription(TestingSemantics.NEWS_LIST)
            .assertExists()
        //long list
        composeTestRule
            .onNodeWithText(
                TestData.articleWithLongTextList[0].title!!,
                substring = true
            )
            .assertExists()
            .assertHasClickAction()



    }


}

