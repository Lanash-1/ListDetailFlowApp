package com.example.listdetailflowapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)

        val adapter = ViewPagerAdapter(supportFragmentManager,lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager){tab, position ->
            when(position){
                0 -> {
                    tab.text = "ALL FILES"
                }
                1 -> {
                    tab.text = "PDF"
                }
                2 -> {
                    tab.text = "IMAGES"
                }
                3 -> {
                    tab.text = "APK"
                }
                4 -> {
                    tab.text = "TEXT"
                }
                5 -> {
                    tab.text = "DOCS"
                }
                6 -> {
                    tab.text = "PPT"
                }
                7 -> {
                    tab.text = "ZIP"
                }
            }
        }.attach()

    }
}

