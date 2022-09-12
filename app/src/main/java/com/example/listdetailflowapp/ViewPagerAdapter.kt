package com.example.listdetailflowapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(
    fm: FragmentManager,
    behavior: Int
): FragmentStateAdapter() {

    val fragment = ListFragment()

    init {
        super(fm, behavior)
    }

    override fun getItemCount(): Int {
        return 8
    }

    override fun createFragment(position: Int): Fragment {
        return fragment
    }

}