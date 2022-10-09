package com.example.listdetailflowapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.listdetailflowapp.adapters.DetailsPagerAdapter
import com.example.listdetailflowapp.databinding.FragmentDetailsBinding
import com.example.listdetailflowapp.dataclass.File
import com.example.listdetailflowapp.interfaces.OnClickDatePicker
import com.example.listdetailflowapp.viewmodel.FilesViewModel

class DetailsFragment : Fragment() {

    private val viewModel: FilesViewModel by activityViewModels()

    private lateinit var binding: FragmentDetailsBinding

    var editedPosition: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val position = viewModel.filePosition

        val fileList: List<File> = if(viewModel.isSearched){
            listOf(viewModel.finalList[position])
        }else{
            viewModel.modifiedList.value!!
        }


        val viewPager = binding.detailViewPager
        val adapter = DetailsPagerAdapter()
        adapter.setList(fileList)
        viewPager.adapter = adapter
        viewPager.setCurrentItem(position, false)


        adapter.setOnClickDatePicker(object : OnClickDatePicker {

            override fun onIconClick(absoluteAdapterPosition: Int) {
                editedPosition = absoluteAdapterPosition
                val datePickerFragment = DatePickerFragment()
                datePickerFragment.show(parentFragmentManager, "datePicker")
            }
        })

        viewModel.edited.observe(viewLifecycleOwner, Observer{
            val fileId = fileList[editedPosition].id
            viewModel.fileList[fileId - 1].createdDate = "${viewModel.date}/${viewModel.month}/${viewModel.year}"
            fileList[editedPosition].createdDate = viewModel.fileList[fileId - 1].createdDate
            adapter.setList(fileList)
            adapter.notifyDataSetChanged()
//            viewPager.adapter = adapter
//            viewPager.setCurrentItem(editedPosition, false)
        })
    }


}