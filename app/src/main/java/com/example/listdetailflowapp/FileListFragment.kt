package com.example.listdetailflowapp

import android.content.Intent
import android.os.Bundle
import android.telecom.Call
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class FileListFragment : Fragment() {

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_file_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println(arguments?.getInt("object"))
        val position = arguments?.getInt("object")
        var files: List<File> = listOf()
        when(position){
            0 -> {
                files = fileList
            }
            1 -> {
                files = fileList.filter { it.fileExtension == "pdf" }
            }
            2 -> {
                files = fileList.filter { it.fileExtension == "jpg" || it.fileExtension == "jpeg" }
            }
            3 -> {
                files = fileList.filter { it.fileExtension == "apk" }
            }
            4 -> {
                files = fileList.filter { it.fileExtension == "txt" }
            }
            5 -> {
                files = fileList.filter { it.fileExtension == "doc" }
            }
            6 -> {
                files = fileList.filter { it.fileExtension == "pptx" }
            }
            7 -> {
                files = fileList.filter { it.fileExtension == "zip" }
            }
        }
        val adapter = FileAdapter(files)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : OnItemClickListener{
            override fun onItemClick(position: Int) {
                println("Position: $position")
//                val viewModel: FileViewModel by viewModels()
//                viewModel.file = files[position]
//                println(viewModel.file)
                val file = files[position]
                val intent = Intent(activity, DetailsActivity::class.java).apply {
                    putExtra("FILE", file)
                }
                startActivity(intent)
            }
        })
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

}