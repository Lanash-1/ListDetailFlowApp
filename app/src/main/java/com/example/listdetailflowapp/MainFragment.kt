package com.example.listdetailflowapp

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.example.listdetailflowapp.databinding.FragmentMainBinding
import com.example.listdetailflowapp.dataclass.File
import com.google.android.material.tabs.TabLayoutMediator
import java.util.*


class MainFragment : Fragment() {


    private lateinit var binding: FragmentMainBinding

    val viewModel: FilesViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)
        val item = menu.findItem(R.id.search)
        val searchView = item.actionView as SearchView

        if(viewModel.queryString!!.isNotEmpty()){
            searchView.isIconified = true
            item.expandActionView()
            searchView.setQuery(viewModel.queryString, false)
            searchView.isFocusable = true
            viewModel.searchedList.value = viewModel.modifiedList.value?.filter {
                it.fileName.lowercase().contains(viewModel.queryString)
            }!!
        }

        viewModel.position.observe(this, Observer{
            activity?.invalidateOptionsMenu()
            if(it != 0){
                menu.findItem(R.id.filter).isVisible = false
            }
        })


        searchView.setOnCloseListener(object : SearchView.OnCloseListener{
            override fun onClose(): Boolean {
                viewModel.queryString = ""
                viewModel.searchedList.value = viewModel.modifiedList.value
                return true
            }

        })


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText!!.isNotEmpty()){
                    viewModel.searchedList.value = viewModel.modifiedList.value?.filter {
                        it.fileName.lowercase(Locale.getDefault()).contains(newText.lowercase())
                    }!!
                    viewModel.queryString = newText
                    if(viewModel.moved){
                        viewModel.moved = false
                    }
                }else{
                    if(!viewModel.moved){
                        viewModel.moved = false
                        viewModel.queryString = newText
                        viewModel.searchedList.value = viewModel.modifiedList.value!!
                    }
                }
                return true
            }

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun modifyList(position: Int, files: List<File>){

        when(position){
            0 -> {
                viewModel.modifiedList.value = files
            }
            1 -> {
                viewModel.modifiedList.value = files.filter {
                    it.fileExtension == "pdf"
                }
            }
            2 -> {
                viewModel.modifiedList.value = files.filter {
                    it.fileExtension == "jpg" || it.fileExtension == "jpeg"
                }
            }
            3 -> {
                viewModel.modifiedList.value = files.filter {
                    it.fileExtension == "apk"
                }
            }
            4 -> {
                viewModel.modifiedList.value = files.filter {
                    it.fileExtension == "txt"
                }
            }
            5 -> {
                viewModel.modifiedList.value = files.filter {
                    it.fileExtension == "doc"
                }
            }
            6 -> {
                viewModel.modifiedList.value = files.filter {
                    it.fileExtension == "pptx"
                }
            }
            7 -> {
                viewModel.modifiedList.value = files.filter {
                    it.fileExtension == "zip"
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val tabLayout = binding.tabLayout
        val viewPager = binding.viewPager

        val files = viewModel.fileList
        println("files = $files")

        viewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    modifyList(position, files)
                    viewModel.position.value = position
                }
            }
        )

        val adapter = ViewPagerAdapter(parentFragmentManager, lifecycle)

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