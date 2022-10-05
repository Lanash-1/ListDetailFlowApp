package com.example.listdetailflowapp.dataclass

import java.io.Serializable

data class File(
    val image: Int,
    val fileName: String,
    val fileExtension: String,
    val location: String,
    val size: Double,
    val sizeExtension: String,
    var createdDate: String,
    val hidden: Boolean,
    val readable: Boolean,
    val writable: Boolean
) : Serializable
