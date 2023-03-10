package com.umut.newsapp.ui.activities

import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayoutMediator
import com.umut.newsapp.R
import com.umut.newsapp.adapters.fragment_state_adapters.MainFragmentsAdapter
import com.umut.newsapp.databinding.ActivityMainBinding
import com.umut.newsapp.enums.Country
import com.umut.newsapp.models.News
import com.umut.newsapp.ui.viewmodels.MainViewModel
import com.umut.newsapp.utils.Constants
import com.umut.newsapp.utils.Constants.BR
import com.umut.newsapp.utils.Constants.BRAZIL
import com.umut.newsapp.utils.Constants.FR
import com.umut.newsapp.utils.Constants.FRANCE
import com.umut.newsapp.utils.Constants.IT
import com.umut.newsapp.utils.Constants.ITALIA
import com.umut.newsapp.utils.Constants.TR
import com.umut.newsapp.utils.Constants.TURKIYE
import com.umut.newsapp.utils.Constants.UNITED_STATES
import com.umut.newsapp.utils.Constants.US
import com.umut.newsapp.utils.Constants.countryCodesMap
import com.umut.newsapp.utils.NetworkStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    private var totalResponseCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.mainLayout.toolbarMain)
        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerMain,
            binding.mainLayout.toolbarMain,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawerMain.addDrawerListener(toggle)
        toggle.syncState()

        binding.navViewMain.setNavigationItemSelectedListener(this)
        onNavigationItemSelected(
            binding.navViewMain.menu.getItem(Country.TURKIYE.order).setChecked(true)
        )

        if (isNetworkAvailable(applicationContext)) {
            getAllNews()
        } else {
            showAlertDialog(
                "WARNING",
                "Network is not available. Please check your internet connection."
            ) {
                finish()
            }
        }

        observeLiveDataChanges()
    }

    private fun getAllNews() {
        Constants.countryCodesMap[UNITED_STATES]?.let { mainViewModel.getNews(it) }
        Constants.countryCodesMap[TURKIYE]?.let { mainViewModel.getNews(it) }
        Constants.countryCodesMap[FRANCE]?.let { mainViewModel.getNews(it) }
        Constants.countryCodesMap[BRAZIL]?.let { mainViewModel.getNews(it) }
        Constants.countryCodesMap[ITALIA]?.let { mainViewModel.getNews(it) }
    }

    private fun observeLiveDataChanges() {
        with(mainViewModel) {

            errorLiveData.observe(this@MainActivity) {
                if (it.isNotEmpty()) {
                    showAlertDialog(
                        "WARNING",
                        "Some error occured. Detail: ${it}."
                    ) {
                        finish()
                    }
                }
            }

            newsLiveData[TR]?.observe(this@MainActivity) {
                trNews = when (it) {
                    is NetworkStatus.Error -> {
                        null
                    }
                    is NetworkStatus.Loading -> {
                        null
                    }
                    is NetworkStatus.Success -> {
                        if (++totalResponseCount == countryCodesMap.size) {
                            hideShimmerLayout()
                            initViewPager()
                        }
                        it.data
                    }
                    else -> {
                        null
                    }
                }
            }

            newsLiveData[US]?.observe(this@MainActivity) {
                usNews = when (it) {
                    is NetworkStatus.Error -> {
                        null
                    }
                    is NetworkStatus.Loading -> {
                        null
                    }
                    is NetworkStatus.Success -> {
                        if (++totalResponseCount == countryCodesMap.size) {
                            hideShimmerLayout()
                            initViewPager()
                        }
                        it.data
                    }
                    else -> {
                        null
                    }
                }
            }

            newsLiveData[FR]?.observe(this@MainActivity) {
                frNews = when (it) {
                    is NetworkStatus.Error -> {
                        null
                    }
                    is NetworkStatus.Loading -> {
                        null
                    }
                    is NetworkStatus.Success -> {
                        if (++totalResponseCount == countryCodesMap.size) {
                            hideShimmerLayout()
                            initViewPager()
                        }
                        it.data
                    }
                    else -> {
                        null
                    }
                }
            }

            newsLiveData[BR]?.observe(this@MainActivity) {
                brNews = when (it) {
                    is NetworkStatus.Error -> {
                        null
                    }
                    is NetworkStatus.Loading -> {
                        null
                    }
                    is NetworkStatus.Success -> {
                        if (++totalResponseCount == countryCodesMap.size) {
                            hideShimmerLayout()
                            initViewPager()
                        }
                        it.data
                    }
                    else -> {
                        null
                    }
                }
            }

            newsLiveData[IT]?.observe(this@MainActivity) {
                itNews = when (it) {
                    is NetworkStatus.Error -> {
                        null
                    }
                    is NetworkStatus.Loading -> {
                        null
                    }
                    is NetworkStatus.Success -> {
                        if (++totalResponseCount == countryCodesMap.size) {
                            hideShimmerLayout()
                            initViewPager()
                        }
                        it.data
                    }
                    else -> {
                        null
                    }
                }
            }
        }
    }

    private fun hideShimmerLayout() {
        binding.mainLayout.shimmerMain.stopShimmer()
        binding.mainLayout.shimmerMain.hideShimmer()
        binding.mainLayout.shimmerMain.visibility = View.GONE
    }

    private fun initViewPager() {
        val viewPagerAdapter = MainFragmentsAdapter(supportFragmentManager, lifecycle)
        binding.mainLayout.viewPagerMain.adapter = viewPagerAdapter

        TabLayoutMediator(
            binding.mainLayout.tabLayoutMain,
            binding.mainLayout.viewPagerMain
        ) { tab, position ->
            val countries = Constants.countryCodesMap.keys.toList()
            tab.text = countries[position]
        }.attach()
        binding.mainLayout.viewPagerMain.visibility = View.VISIBLE
    }

    /**
     * Switch Fragments in a ViewPager on clicking items in Navigation Drawer by menu item id
     */
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId


        if (id == R.id.nav_turkiye) {
            binding.mainLayout.viewPagerMain.currentItem = Country.TURKIYE.order
        } else if (id == R.id.nav_us) {
            binding.mainLayout.viewPagerMain.currentItem = Country.UNITED_STATES.order
        } else if (id == R.id.nav_france) {
            binding.mainLayout.viewPagerMain.currentItem = Country.FRANCE.order
        } else if (id == R.id.nav_britain) {
            binding.mainLayout.viewPagerMain.currentItem = Country.BRAZIL.order
        } else if (id == R.id.nav_italia) {
            binding.mainLayout.viewPagerMain.currentItem = Country.ITALIA.order
        }

        binding.drawerMain.closeDrawer(GravityCompat.START)
        return true
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                    ?: return false
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            if (connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!.isConnectedOrConnecting) {
                return true
            }
        }
        return false
    }

    private fun showAlertDialog(
        title: String,
        message: String,
        todo: () -> Unit
    ) {
        val dialogBuilder = AlertDialog.Builder(this@MainActivity)
        dialogBuilder.setMessage(message)
            .setTitle(title)
            .setCancelable(false)
            .setPositiveButton("OK", DialogInterface.OnClickListener { dialog, id ->
                todo()
                dialog.dismiss()
            }).create().show()
    }

    companion object {
        var trNews: News? = null
        var usNews: News? = null
        var frNews: News? = null
        var brNews: News? = null
        var itNews: News? = null
    }

}