package com.umut.newsapp.managers

import com.umut.newsapp.BuildConfig

object Globals {
    /**
     * @param  baseUrl and
     * @param  apiKey init from build.gradle (NewsApp.app) as buildConfigurationParameter
     */
    const val baseUrl = BuildConfig.API_URL
    const val apiKey = BuildConfig.API_KEY
}