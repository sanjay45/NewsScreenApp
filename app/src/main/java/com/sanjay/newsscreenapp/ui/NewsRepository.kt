package com.sanjay.newsscreenapp.ui

import com.sanjay.newsscreenapp.network.NewsApi
import com.sanjay.newsscreenapp.network.NewsApiService

object NewsRepository {

    suspend fun getArticles() = NewsApi.api.getArticles()

}