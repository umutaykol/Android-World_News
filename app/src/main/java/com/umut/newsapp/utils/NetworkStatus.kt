package com.umut.newsapp.utils

sealed class NetworkStatus<T> {
    class Loading<T>(val isLoading: Boolean) : NetworkStatus<T>()
    class Error<T>(val error: String) : NetworkStatus<T>()
    class Success<T>(val data: T) : NetworkStatus<T>()
}
