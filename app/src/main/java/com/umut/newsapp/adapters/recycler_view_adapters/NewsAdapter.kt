package com.umut.newsapp.adapters.recycler_view_adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.umut.newsapp.R
import com.umut.newsapp.databinding.NewsAdapterItemBinding
import com.umut.newsapp.models.Article
import com.umut.newsapp.ui.activities.NewsDetailActivity
import com.umut.newsapp.utils.Constants

class NewsAdapter() : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(), ItemClickListener {

    class NewsViewHolder(var view: NewsAdapterItemBinding) : RecyclerView.ViewHolder(view.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<NewsAdapterItemBinding>(
            inflater,
            R.layout.news_adapter_item,
            parent,
            false
        )
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.view.article = differ.currentList[position]
        holder.view.onClickListener = this
    }

    override fun onClicked(view: View, article: Article) {
        Intent(view.context, NewsDetailActivity::class.java).apply {
            view.context.startActivity(
                Intent(view.context, NewsDetailActivity::class.java).apply {
                    putExtra(Constants.NEWS_URL, article?.url)
                    putExtra(Constants.NEWS_TITLE, article?.title)
                    putExtra(Constants.NEWS_IMAGE_URL, article?.urlToImage)
                    putExtra(Constants.NEWS_DESCRIPTION, article?.description)
                    putExtra(Constants.NEWS_PUBLICATION_TIME, article?.publishedAt)
                    putExtra(Constants.NEWS_CONTENT, article?.content)
                }
            )
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}