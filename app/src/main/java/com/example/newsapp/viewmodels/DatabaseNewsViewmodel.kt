package com.example.newsapp.viewmodels

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import com.example.newsapp.common.GlobalState

import com.example.newsapp.data.remote.models.Article
import com.example.newsapp.di.Database
import com.example.newsapp.di.Network
import com.example.newsapp.domain.NewsRepository
import com.example.newsapp.interfaces.DispatchersProvider
import com.example.newsapp.interfaces.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class DatabaseNewsViewmodel @Inject constructor(@Database override val newsRepository: NewsRepository,
                                                @Database override val networkPager: Pager<Int, Article>,
                                                override val logger: Logger,
                                                override val dispatcherProvider: DispatchersProvider): NewsViewModel(newsRepository,networkPager,logger,dispatcherProvider)  {
     fun saveArticles(article: Article) {
         viewModelScope.launch {
             withContext(dispatcherProvider.io){
                 if(GlobalState.canSave(article)){
                     GlobalState.addToSaved(article)
                     newsRepository.saveArticles(article)
                 }
                 else{
                     GlobalState.removeFromSaved(article)
                     newsRepository.deleteArticle(article)
                 }

             }
         }
    }
}