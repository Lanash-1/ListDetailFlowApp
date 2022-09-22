package com.example.listdetailflowapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.listdetailflowapp.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {

    private val viewModel: FilesViewModel by activityViewModels()

    lateinit var binding: FragmentDetailsBinding

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

        val fileList = viewModel.modifiedList.value


        val viewPager = binding.detailViewPager
        val adapter = DetailsPagerAdapter(fileList!!)
        viewPager.adapter = adapter
        viewPager.setCurrentItem(position, false)




    }
}