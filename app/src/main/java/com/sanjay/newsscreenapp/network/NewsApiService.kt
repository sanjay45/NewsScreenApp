package com.sanjay.newsscreenapp.network

import com.sanjay.newsscreenapp.model.NewsApiResponse
import com.sanjay.newsscreenapp.utils.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("everything")
    suspend fun getArticles(
        @Query("q") q: String = "tesla",
        @Query("from") from:String = "2021-05-15",
        @Query("sortBy") sortBy:String = "publishedAt",
        @Query("apiKey") apiKey:String = API_KEY
    ): Response<NewsApiResponse>
}