package com.umut.newsapp.utils

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.umut.newsapp.R
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun ImageView.downloadFromUrl(url: String?, progressDrawable: CircularProgressDrawable) {
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.drawable.ic_launcher_foreground)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}

fun placeholderProgressBar(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}

fun convertDate(stringDate: String?): String {
    stringDate?.let {
        val date = formattedStringDateToDateConverter(stringDate, SimpleDateFormat("yyyy-MM-dd"))
        return dateToFormattedStringDateConverter(date, SimpleDateFormat("dd.MM.yyyy"))
    } ?: return ""
}

fun dateToFormattedStringDateConverter(date: Date?, dateFormat: DateFormat): String {
    return date?.let { dateFormat.format(it) } ?: ""
}

fun formattedStringDateToDateConverter(dateStr: String?, dateFormat: DateFormat): Date? {
    var date: Date? = null
    try {
        date = dateStr?.let { dateFormat.parse(it) }
    } catch (exc: ParseException) {
        exc.printStackTrace()
    } catch (exc: Exception) {
        exc.printStackTrace()
    }
    return date
}
