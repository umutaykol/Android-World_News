package com.umut.newsapp.network_di

import com.umut.newsapp.repositories.MainRepository
import com.umut.newsapp.repositories.MainRepositoryImplementation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

interface RepositoryDi {
    @Module
    @InstallIn(SingletonComponent::class)
    interface RepositoryModules {
        @Binds
        fun provideMainRepositoryImpl(repository: MainRepositoryImplementation): MainRepository
    }
}