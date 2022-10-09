package com.example.listdetailflowapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listdetailflowapp.interfaces.OnItemClickListener
import com.example.listdetailflowapp.databinding.ItemFileBinding
import com.example.listdetailflowapp.dataclass.File

class FileAdapter (

) : RecyclerView.Adapter<FileAdapter.FileViewHolder>(), OnItemClickListener {
    private lateinit var files: List<File>
    private lateinit var listener: OnItemClickListener
    fun setFiles(files: List<File>){
        this.files = files
    }
    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }

    class FileViewHolder(val binding: ItemFileBinding, listener: OnItemClickListener) : RecyclerView.ViewHolder(binding.root){
        init {
            itemView.setOnClickListener{
                listener.onItemClick(absoluteAdapterPosition)
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
            sizeText.text = "Size: ${files[position].size} ${files[position].sizeExtension}"
            fileIcon.setImageResource(files[position].image)
        }
    }

    override fun getItemCount(): Int {
        return files.size
    }

    override fun onItemClick(position: Int) {
        TODO("Not yet implemented")
    }

}