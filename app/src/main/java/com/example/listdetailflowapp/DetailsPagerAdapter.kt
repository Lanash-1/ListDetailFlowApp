package com.example.listdetailflowapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listdetailflowapp.databinding.ItemDetailsBinding
import com.example.listdetailflowapp.dataclass.File

class DetailsPagerAdapter(
    private val list: List<File>
): RecyclerView.Adapter<DetailsPagerAdapter.DetailsPagerViewHolder>() {

    inner class DetailsPagerViewHolder(val binding: ItemDetailsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsPagerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemDetailsBinding.inflate(layoutInflater, parent, false)
        return DetailsPagerViewHolder(binding)
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
}