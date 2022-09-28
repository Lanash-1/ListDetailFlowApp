package com.example.listdetailflowapp

import android.os.Bundle
import android.view.*
import androidx.fragment.app.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listdetailflowapp.adapters.FileAdapter
import com.example.listdetailflowapp.viewmodel.FilesViewModel

class FileListFragment : Fragment() {

    private var adapter = FileAdapter()
    private val viewModel: FilesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_file_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val position = arguments?.getInt("object")
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)

        adapter.setOnItemClickListener(object : OnItemClickListener{
            override fun onItemClick(position: Int) {
                viewModel.filePosition = position
                viewModel.position.value = -1
                if(viewModel.queryString.isEmpty()){
                    viewModel.isSearched = false
                    viewModel.finalList = viewModel.modifiedList.value!!
//                    viewModel.moved = true
                }else{
                    viewModel.isSearched = true
                    viewModel.finalList = viewModel.searchedList.value!!
                }
                viewModel.moved = true
                parentFragmentManager.commit {
                    setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out)
//                    setCustomAnimations(R.anim.slide_in, R.anim.fade_out)
                    replace(R.id.mainFragment, DetailsFragment())
                    addToBackStack(null)
                }
            }
        })

        recyclerView.layoutManager = LinearLayoutManager(context)

//        recyclerView.layoutManager = GridLayoutManager(context,2)

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