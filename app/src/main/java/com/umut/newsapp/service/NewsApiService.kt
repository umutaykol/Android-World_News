package com.umut.newsapp.service

import com.umut.newsapp.models.News
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("v2/top-headlines")
    suspend fun getNews(
        @Query("country") countryCode: String,
        @Query("apiKey") apiKey: String
    ): News
}