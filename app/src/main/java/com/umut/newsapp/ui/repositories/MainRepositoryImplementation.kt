package com.umut.newsapp.ui.repositories

import com.umut.newsapp.managers.Globals
import com.umut.newsapp.service.NewsApiService
import com.umut.newsapp.utils.NetworkStatus
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepositoryImplementation @Inject constructor(
    private val newsApiService: NewsApiService
) : MainRepository {

    override suspend fun getNews(countryCode: String) = flow {

        emit(NetworkStatus.Loading(isLoading = true))

        val response = newsApiService.getNews(
            countryCode,
            Globals.apiKey
        )

        emit(NetworkStatus.Success(response))

    }.catch { err ->
        emit(NetworkStatus.Error(err.message ?: "Unknown Error"))
    }

}