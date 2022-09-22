package com.example.listdetailflowapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FileListFragment : Fragment() {

    private var adapter = FileAdapter()

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
        val viewModel: FilesViewModel by activityViewModels()
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
                parentFragmentManager.commit {
                    replace(R.id.mainFragment, DetailsFragment())
                }
            }
        })

        recyclerView.layoutManager = LinearLayoutManager(context)

        println("IN FILE LIST: ${viewModel.modifiedList.value}")

        viewModel.modifiedList.observe(viewLifecycleOwner,Observer{
            println("Data class $it")
            adapter.setFiles(it)
            recyclerView.adapter = adapter
        })
    }
}