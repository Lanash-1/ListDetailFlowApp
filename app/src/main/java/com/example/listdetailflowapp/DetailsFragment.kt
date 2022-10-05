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

    lateinit var binding: FragmentDetailsBinding

    var editedPosition: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val position = viewModel.filePosition

        var fileList: List<File>

        if(viewModel.isSearched){
            fileList = listOf(viewModel.finalList[position])
        }else{
            fileList = viewModel.modifiedList.value!!
        }

        val viewPager = binding.detailViewPager
        var adapter = DetailsPagerAdapter(fileList)
        viewPager.adapter = adapter
        viewPager.setCurrentItem(position, false)


        adapter.setOnClickDatePicker(object : OnClickDatePicker {
            override fun onIconClick(absoluteAdapterPosition: Int) {
                val datePickerFragment = DatePickerFragment()
                datePickerFragment.show(parentFragmentManager, "datePicker")
                fileList[absoluteAdapterPosition].createdDate = "${viewModel.date}/${viewModel.month}/${viewModel.year}"
                editedPosition = absoluteAdapterPosition
                viewModel.dateText.value = "${viewModel.date}/${viewModel.month}/${viewModel.year}"
            }
        })

        viewModel.dateText.observe(viewLifecycleOwner, Observer{
            println("DATE CHANGED - ${viewModel.dateText.value}")
        })
    }
}