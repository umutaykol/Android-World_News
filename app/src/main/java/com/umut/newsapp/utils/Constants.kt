package com.umut.newsapp.utils

object Constants {
    const val TOTAL_NEWS_TAB = 5

    val countryCodesMap: Map<String, String>
        get() = mapOf(
            Pair("TURKIYE", "TR"),
            Pair("UNITED STATES", "US"),
            Pair("FRANCE", "FR"),
            Pair("BRITAIN", "BR"),
            Pair("ITALIA", "IT")
        )

    const val NEWS_URL = "news url"
    const val NEWS_TITLE = "news title"
    const val NEWS_IMAGE_URL = "news image url"
    const val NEWS_PUBLICATION_TIME = "news publication time"
    const val NEWS_DESCRIPTION = "news description"
    const val NEWS_CONTENT = "news content"
}