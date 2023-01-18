package com.umut.newsapp.adapters.fragment_state_adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.umut.newsapp.enums.Country
import com.umut.newsapp.ui.fragments.*
import com.umut.newsapp.utils.Constants.TOTAL_NEWS_TAB

class MainFragmentsAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int = TOTAL_NEWS_TAB

    override fun createFragment(position: Int): Fragment = when (position) {
        Country.TURKIYE.order -> {
            TrNewsFragment()
        }
        Country.UNITED_STATES.order -> {
            UsNewsFragment()
        }
        Country.FRANCE.order -> {
            FrNewsFragment()
        }
        Country.BRAZIL.order -> {
            BrNewsFragment()
        }
        Country.ITALIA.order -> {
            ItNewsFragment()
        }
        else -> TrNewsFragment()
    }
}
