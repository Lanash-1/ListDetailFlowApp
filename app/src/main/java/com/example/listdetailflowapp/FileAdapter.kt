package com.example.listdetailflowapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listdetailflowapp.databinding.ItemFileBinding


class FileAdapter (
    var files: List<File>
) : RecyclerView.Adapter<FileAdapter.FileViewHolder>(), OnItemClickListener{

    lateinit var listener: OnItemClickListener

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }

    class FileViewHolder(val binding: ItemFileBinding, listener: OnItemClickListener) : RecyclerView.ViewHolder(binding.root){
        init {
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemFileBinding.inflate(layoutInflater, parent, false)
        return FileViewHolder(binding, listener)
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

    override fun onItemClick(position: Int) {
        TODO("Not yet implemented")
    }

}