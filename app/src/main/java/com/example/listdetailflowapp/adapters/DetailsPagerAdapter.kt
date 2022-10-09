package com.example.listdetailflowapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listdetailflowapp.databinding.ItemDetailsBinding
import com.example.listdetailflowapp.dataclass.File
import com.example.listdetailflowapp.interfaces.OnClickDatePicker

class DetailsPagerAdapter: RecyclerView.Adapter<DetailsPagerAdapter.DetailsPagerViewHolder>(), OnClickDatePicker {

    private lateinit var list: List<File>

    fun setList(list: List<File>){
        this.list = list
    }

    private lateinit var listener: OnClickDatePicker

    fun setOnClickDatePicker(listener: OnClickDatePicker){
        this.listener = listener
    }

    class DetailsPagerViewHolder(val binding: ItemDetailsBinding, listener: OnClickDatePicker) : RecyclerView.ViewHolder(binding.root)
        {
        init {
            val datePicker = binding.datePickerIcon
            datePicker.setOnClickListener {
                listener.onIconClick(absoluteAdapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsPagerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemDetailsBinding.inflate(layoutInflater, parent, false)
        return DetailsPagerViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: DetailsPagerViewHolder, position: Int) {
        val file: File = list[position]

        holder.binding.apply {
            fileName.text = "${file.fileName}.${file.fileExtension}"
            location.text = file.location
            size.text = "${file.size} ${file.sizeExtension}"
            date.text = file.createdDate
            readable.apply {
                if (file.readable) {
                    this.text = "Yes"
                } else {
                    this.text = "No"
                }
            }

            hidden.apply {
                if (file.hidden) {
                    this.text = "Yes"
                } else {
                    this.text = "No"
                }
            }

            writable.apply {
                if (file.writable) {
                    this.text = "Yes"
                } else {
                    this.text = "No"
                }
            }

            fileImage.setImageResource(file.image)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onIconClick(absoluteAdapterPosition: Int) {
        TODO("Not yet implemented")
    }
}