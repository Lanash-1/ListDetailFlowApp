package com.example.listdetailflowapp

data class File(
    val fileName: String,
    val fileExtension: String,
    val location: String,
    val size: Double,
    val sizeExtension: String,
    val createdDate: String,
    val hidden: Boolean,
    val readable: Boolean,
    val writable: Boolean
)
