package com.umut.newsapp.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("android:convertTime")
fun dateTimeConverter(view: TextView, time: String?) {
    view.text = convertDate(time)
}

@BindingAdapter("android:downloadUrl")
fun downloadImage(view: ImageView, url: String?) {
    view.downloadFromUrl(url, placeholderProgressBar(view.context))
}