package com.umut.newsapp.adapters.recycler_view_adapters

import android.view.View
import com.umut.newsapp.models.Article

interface ItemClickListener {

    fun onClicked(view: View, article: Article)

}