package com.umut.newsapp.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
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

        getAllNews()

        observeLiveDataChanges()

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
    }

    private fun getAllNews() {
//        Constants.countryCodesMap["UNITED STATES"]?.let { mainViewModel.getNews(it) }
//        Constants.countryCodesMap["TURKIYE"]?.let { mainViewModel.getNews(it) }
//        Constants.countryCodesMap["FRANCE"]?.let { mainViewModel.getNews(it) }
//        Constants.countryCodesMap["BRITAIN"]?.let { mainViewModel.getNews(it) }
        Constants.countryCodesMap["ITALIA"]?.let { mainViewModel.getNews(it) }
    }

    private fun observeLiveDataChanges() {
        with(mainViewModel) {

            newsLiveData["TR"]?.observe(this@MainActivity) {
                trNews = when (it) {
                    is NetworkStatus.Error -> {
                        null
                    }
                    is NetworkStatus.Loading -> {
                        null
                    }
                    is NetworkStatus.Success -> {
                        if (++totalResponseCount == 5) {
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

            newsLiveData["US"]?.observe(this@MainActivity) {
                usNews = when (it) {
                    is NetworkStatus.Error -> {
                        null
                    }
                    is NetworkStatus.Loading -> {
                        null
                    }
                    is NetworkStatus.Success -> {
                        if (++totalResponseCount == 5) {
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

            newsLiveData["FR"]?.observe(this@MainActivity) {
                frNews = when (it) {
                    is NetworkStatus.Error -> {
                        null
                    }
                    is NetworkStatus.Loading -> {
                        null
                    }
                    is NetworkStatus.Success -> {
                        if (++totalResponseCount == 5) {
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

            newsLiveData["BR"]?.observe(this@MainActivity) {
                brNews = when (it) {
                    is NetworkStatus.Error -> {
                        null
                    }
                    is NetworkStatus.Loading -> {
                        null
                    }
                    is NetworkStatus.Success -> {
                        if (++totalResponseCount == 5) {
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

            newsLiveData["IT"]?.observe(this@MainActivity) {
                itNews = when (it) {
                    is NetworkStatus.Error -> {
                        null
                    }
                    is NetworkStatus.Loading -> {
                        null
                    }
                    is NetworkStatus.Success -> {
                        if (++totalResponseCount == 1) {
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        // Switch Fragments in a ViewPager on clicking items in Navigation Drawer by menu item id
        if (id == R.id.nav_turkiye) {
            binding.mainLayout.viewPagerMain.currentItem = Country.TURKIYE.order
        } else if (id == R.id.nav_us) {
            binding.mainLayout.viewPagerMain.currentItem = Country.UNITED_STATES.order
        } else if (id == R.id.nav_france) {
            binding.mainLayout.viewPagerMain.currentItem = Country.FRANCE.order
        } else if (id == R.id.nav_britain) {
            binding.mainLayout.viewPagerMain.currentItem = Country.BRITAIN.order
        } else if (id == R.id.nav_italia) {
            binding.mainLayout.viewPagerMain.currentItem = Country.ITALIA.order
        }

        binding.drawerMain.closeDrawer(GravityCompat.START)
        return true
    }

    companion object {
        var trNews: News? = null
        var usNews: News? = null
        var frNews: News? = null
        var brNews: News? = null
        var itNews: News? = null
    }

}