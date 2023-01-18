package com.umut.newsapp.models

import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("id")
    val sourceId: String?,

    @SerializedName("name")
    val sourceName: String?
)