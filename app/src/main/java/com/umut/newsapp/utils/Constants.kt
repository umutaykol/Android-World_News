package com.umut.newsapp.utils

object Constants {
    const val TOTAL_NEWS_TAB = 5

    val countryCodesMap: Map<String, String>
        get() = mapOf(
            Pair("TURKIYE", "TR"),
            Pair("UNITED STATES", "US"),
            Pair("FRANCE", "FR"),
            Pair("BRAZIL", "BR"),
            Pair("ITALIA", "IT")
        )

    const val TURKIYE = "TURKIYE"
    const val UNITED_STATES = "UNITED STATES"
    const val FRANCE = "FRANCE"
    const val BRAZIL = "BRAZIL"
    const val ITALIA = "ITALIA"

    const val TR = "TR"
    const val US = "US"
    const val FR = "FR"
    const val BR = "BR"
    const val IT = "IT"

    const val NEWS_URL = "news url"
    const val NEWS_TITLE = "news title"
    const val NEWS_IMAGE_URL = "news image url"
    const val NEWS_PUBLICATION_TIME = "news publication time"
    const val NEWS_DESCRIPTION = "news description"
    const val NEWS_CONTENT = "news content"
}