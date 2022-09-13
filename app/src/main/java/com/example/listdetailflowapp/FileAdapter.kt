package com.example.listdetailflowapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listdetailflowapp.databinding.ItemFileBinding


class FileAdapter (
    var files: List<File>
) : RecyclerView.Adapter<FileAdapter.FileViewHolder>(){

    inner class FileViewHolder(val binding: ItemFileBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemFileBinding.inflate(layoutInflater, parent, false)
        return FileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        holder.binding.apply {
            fileNameText.text = "${files[position].fileName}.${files[position].fileExtension}"
            dateText.text = "Date created: ${files[position].createdDate}"
        }
    }

    override fun getItemCount(): Int {
        return files.size
    }

}