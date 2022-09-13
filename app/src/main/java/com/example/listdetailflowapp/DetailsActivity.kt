package com.example.listdetailflowapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.listdetailflowapp.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val file = intent.getSerializableExtra("FILE") as File
        binding.fileName.text = "${file.fileName}.${file.fileExtension}"
        binding.location.text = file.location
        binding.size.text = "${file.size} ${file.sizeExtension}"
        binding.date.text = file.createdDate
        binding.readable.apply {
            if(file.readable){
                this.text = "Yes"
            }else{
                this.text = "No"
            }
        }

        binding.hidden.apply {
            if(file.hidden){
                this.text = "Yes"
            }else{
                this.text = "No"
            }
        }

        binding.writable.apply {
            if(file.writable){
                this.text = "Yes"
            }else{
                this.text = "No"
            }
        }


    }
}