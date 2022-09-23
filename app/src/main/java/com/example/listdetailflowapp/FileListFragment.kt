package com.example.listdetailflowapp

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class FileListFragment : Fragment() {

    private var adapter = FileAdapter()
    private val viewModel: FilesViewModel by activityViewModels()

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setHasOptionsMenu(true)
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.main, menu)
//        val item = menu.findItem(R.id.search)
//        val searchView = item.actionView as SearchView
//
//        viewModel.position.observe(this, Observer{
//            activity?.invalidateOptionsMenu()
//            if(it != 0){
//                menu.findItem(R.id.filter).isVisible = false
//            }
//        })
//
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                println(newText)
//                if(!newText!!.isEmpty()){
//                    viewModel.searchedList = viewModel.modifiedList.value?.filter {
//                        it.fileName.lowercase(Locale.getDefault()).contains(newText.lowercase())
//                    }!!
//                    /*adapter.setOnItemClickListener(object : OnItemClickListener{
//                        override fun onItemClick(position: Int) {
//                            viewModel.filePosition = position
//                            viewModel.actionTitle = actionTitle
//                            viewModel.position.value = -1
//                            parentFragmentManager.commit {
//                                replace(R.id.mainFragment, DetailsFragment())
//                            }
//                        }
//                    })*/
//
//                    viewModel.emptySearch.value = false
//
//                }else{
//                    viewModel.emptySearch.value = true
//                }
//                return true
//            }
//
//        })
//    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_file_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val position = arguments?.getInt("object")
        var actionTitle = "Files"
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        when(position){
            0 -> {
                actionTitle = "All Files"
            }
            1 -> {
                actionTitle = "PDF's"
            }
            2 -> {
                actionTitle = "Images"
            }
            3 -> {
                actionTitle = "APK's"
            }
            4 -> {
                actionTitle = "Text Files"
            }
            5 -> {
                actionTitle = "DOC's"
            }
            6 -> {
                actionTitle = "PPT's"
            }
            7 -> {
                actionTitle = "ZIP Files"
            }
        }

        adapter.setOnItemClickListener(object : OnItemClickListener{
            override fun onItemClick(position: Int) {
                viewModel.filePosition = position
                viewModel.actionTitle = actionTitle
                viewModel.position.value = -1
                if(viewModel.queryString.isEmpty()){
                    viewModel.isSearched = false
                    viewModel.finalList = viewModel.modifiedList.value!!
                    viewModel.moved = true
                }else{
                    viewModel.isSearched = true
                    viewModel.finalList = viewModel.searchedList.value!!
                }
                parentFragmentManager.commit {
                    replace(R.id.mainFragment, DetailsFragment())
                }
            }
        })

        recyclerView.layoutManager = LinearLayoutManager(context)


        viewModel.modifiedList.observe(viewLifecycleOwner,Observer{
            adapter.setFiles(it)
            recyclerView.adapter = adapter
        })

        viewModel.searchedList.observe(viewLifecycleOwner, Observer{
            adapter.setFiles(it)
            adapter.notifyDataSetChanged()
        })
    }
}