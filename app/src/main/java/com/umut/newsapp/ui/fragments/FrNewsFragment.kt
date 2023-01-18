package com.umut.newsapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jama.carouselview.enums.IndicatorAnimationType
import com.jama.carouselview.enums.OffsetType
import com.umut.newsapp.R
import com.umut.newsapp.adapters.recycler_view_adapters.NewsAdapter
import com.umut.newsapp.databinding.FragmentFrNewsBinding
import com.umut.newsapp.models.Article
import com.umut.newsapp.ui.activities.MainActivity
import com.umut.newsapp.ui.activities.NewsDetailActivity
import com.umut.newsapp.utils.Constants
import com.umut.newsapp.utils.downloadFromUrl
import com.umut.newsapp.utils.placeholderProgressBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FrNewsFragment : Fragment() {

    private var _binding: FragmentFrNewsBinding? = null
    private val binding get() = _binding!!

    private val newsAdapter = NewsAdapter()
    private var newsDataForTopHeadlines: List<Article>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFrNewsBinding.inflate(inflater, container, false)

        newsDataForTopHeadlines = MainActivity.frNews?.articles?.slice(0 until 5)

        initRecyclerView()
        initCarouselView()

        return binding.root
    }

    private fun initCarouselView() {
        binding.carouselFrNews.apply {
            size = newsDataForTopHeadlines?.size ?: 0
            autoPlay = true
            indicatorAnimationType = IndicatorAnimationType.SWAP
            carouselOffset = OffsetType.CENTER
            setCarouselViewListener { view, position ->

                val imageView = view.findViewById<ImageView>(R.id.img)
                imageView.downloadFromUrl(
                    newsDataForTopHeadlines?.get(position)?.urlToImage,
                    placeholderProgressBar(view.context)
                )

                val newsTitle = view.findViewById<TextView>(R.id.headline)
                newsTitle.text = newsDataForTopHeadlines?.get(position)?.title.toString()

                view.setOnClickListener {
                    newsDataForTopHeadlines?.get(position).let {
                        startActivity(
                            Intent(requireContext(), NewsDetailActivity::class.java).apply {
                                putExtra(Constants.NEWS_URL, it?.url)
                                putExtra(Constants.NEWS_TITLE, it?.title)
                                putExtra(Constants.NEWS_IMAGE_URL, it?.urlToImage)
                                putExtra(Constants.NEWS_DESCRIPTION, it?.description)
                                putExtra(Constants.NEWS_PUBLICATION_TIME, it?.publishedAt)
                                putExtra(Constants.NEWS_CONTENT, it?.content)
                            }
                        )
                    }
                }
            }
            show()
        }
    }

    private fun initRecyclerView() {
        binding.recyclerFrNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        MainActivity.itNews?.articles?.let {
            newsAdapter.differ.submitList(it.slice(5..it.lastIndex))
        }
    }
}