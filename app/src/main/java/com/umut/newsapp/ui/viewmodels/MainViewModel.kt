package com.umut.newsapp.ui.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.umut.newsapp.models.News
import com.umut.newsapp.repositories.MainRepository
import com.umut.newsapp.utils.NetworkStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val application: Application,
    private val mainRepository: MainRepository
) : ViewModel() {

    private val newsMutableLiveData: Map<String, MutableLiveData<NetworkStatus<News>>> =
        mapOf(
            Pair("TR", MutableLiveData<NetworkStatus<News>>()),
            Pair("US", MutableLiveData<NetworkStatus<News>>()),
            Pair("FR", MutableLiveData<NetworkStatus<News>>()),
            Pair("BR", MutableLiveData<NetworkStatus<News>>()),
            Pair("IT", MutableLiveData<NetworkStatus<News>>()),
        )

    val newsLiveData: Map<String, LiveData<NetworkStatus<News>>> = newsMutableLiveData


    private val errorMutableLiveData: MutableLiveData<String> = MutableLiveData()
    val errorLiveData: LiveData<String> = errorMutableLiveData

    fun getNews(countryCode: String) {
        when (countryCode) {
            "US" -> {
                viewModelScope.launch {
                    mainRepository.getNews(countryCode).collect {
                        when(it) {
                            is NetworkStatus.Error -> errorMutableLiveData.postValue(it.error)
                            else -> {}
                        }
                        newsMutableLiveData["US"]?.postValue(it)
                    }
                }
            }
            "TR" -> {
                viewModelScope.launch {
                    mainRepository.getNews(countryCode).collect {
                        when(it) {
                            is NetworkStatus.Error -> errorMutableLiveData.postValue(it.error)
                            else -> {}
                        }
                        newsMutableLiveData["TR"]?.postValue(it)
                    }
                }
            }
            "BR" -> {
                viewModelScope.launch {
                    mainRepository.getNews(countryCode).collect {
                        when(it) {
                            is NetworkStatus.Error -> errorMutableLiveData.postValue(it.error)
                            else -> {}
                        }
                        newsMutableLiveData["BR"]?.postValue(it)
                    }
                }
            }
            "FR" -> {
                viewModelScope.launch {
                    mainRepository.getNews(countryCode).collect {
                        when(it) {
                            is NetworkStatus.Error -> errorMutableLiveData.postValue(it.error)
                            else -> {}
                        }
                        newsMutableLiveData["FR"]?.postValue(it)
                    }
                }
            }
            "IT" -> {
                viewModelScope.launch {
                    mainRepository.getNews(countryCode).collect {
                        when(it) {
                            is NetworkStatus.Error -> errorMutableLiveData.postValue(it.error)
                            else -> {}
                        }
                        newsMutableLiveData["IT"]?.postValue(it)
                    }
                }
            }
        }
    }
}