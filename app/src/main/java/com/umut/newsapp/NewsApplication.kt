package com.umut.newsapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Require to use Dagger Hilt
 */
@HiltAndroidApp
class NewsApplication : Application() {
}