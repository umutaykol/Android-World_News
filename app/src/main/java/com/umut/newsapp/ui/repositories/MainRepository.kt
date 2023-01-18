package com.umut.newsapp.ui.repositories

import com.umut.newsapp.models.News
import com.umut.newsapp.utils.NetworkStatus
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun getNews(countryCode: String): Flow<NetworkStatus<News>>

}
