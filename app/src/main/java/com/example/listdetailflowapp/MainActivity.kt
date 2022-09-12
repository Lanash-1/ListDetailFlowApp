package com.example.listdetailflowapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var fileList = mutableListOf(
            File("report","pdf","local storage",1.0,"MB","8/9/22",false,true,true) ,
            File("group photo","jpg","sd card",223.0,"KB","22/12/21",false,true,true),
            File("exam_result","pdf","downloads",2.2,"MB","2/9/17",false,true,true),
            File("details","txt","sd card",1.0,"KB","4/2/15",false,true,true),
            File("resume_draft","doc","downloads",4.54,"MB","22/11/22",false,true,true),
            File("photo_small","jpeg","local storage",99.9,"KB","3/10/19",false,true,true),
            File("dream11","apk","downloads",22.3,"MB","30/2/11",false,true,true),
            File("screenshot_1122","jpeg","local storage",123.0,"KB","4/4/14",false,true,true),
            File("presentation","pptx","sd card",5.6,"MB","6/12/12",false,true,true),
            File("files_compressed","zip","downloads",1.1,"MB","17/2/17",false,true,true)
        )

        var adapter = FileAdapter(fileList)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val viewPager: ViewPager2 = findViewById<ViewPager2>(R.id.viewPager)

        tabLayout.setupWithViewPager(viewPager)

        val viewPagerAdapter = ViewPagerAdapter()




    }
}

