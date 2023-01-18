package com.umut.newsapp.ui.activities

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.jama.carouselview.CarouselView
import com.umut.newsapp.databinding.ActivityNewsDetailBinding
import com.umut.newsapp.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsDetailBinding
    private lateinit var carouselView: CarouselView

    private lateinit var newsUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.toolbarNewsDetail.title =
            intent.getStringExtra(Constants.NEWS_TITLE).toString().substring(0, 24).plus("...")
        newsUrl = intent.getStringExtra(Constants.NEWS_URL).toString()

        initWebView()

        newsUrl?.let {
            binding.webViewNewsDetail.loadUrl(newsUrl)
        }
    }

    private fun initWebView() {
        binding.webViewNewsDetail.settings.supportZoom()
        binding.webViewNewsDetail.apply {
            settings.apply {
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                javaScriptEnabled = true
                loadsImagesAutomatically = true
                domStorageEnabled = true
                allowFileAccess = true
                allowContentAccess = true
                builtInZoomControls = true
                supportZoom()
            }
            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()
        }
    }
}