package com.example.listdetailflowapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.listdetailflowapp.dataclass.File

class FilesViewModel: ViewModel(){
    var modifiedList = MutableLiveData<List<File>>()

    var searchedList = MutableLiveData<List<File>>()

    var finalList = listOf<File>()

    var position = MutableLiveData<Int>()

    var fileList = mutableListOf(
        File(R.drawable.pdficon,"Report","pdf","local storage",1.0,"MB","8/9/22",false,true,true) ,
        File(R.drawable.imageicon,"group photo","jpg","sd card",223.0,"KB","22/12/21",false,true,true),
        File(R.drawable.pdficon,"Exam_result","pdf","downloads",2.2,"MB","2/9/17",false,true,true),
        File(R.drawable.txticon,"details","txt","sd card",1.0,"KB","4/2/15",false,true,true),
        File(R.drawable.docicon,"Resume_draft","doc","downloads",4.54,"MB","22/11/22",false,true,true),
        File(R.drawable.imageicon,"photo_small","jpeg","local storage",99.9,"KB","3/10/19",false,true,true),
        File(R.drawable.apkicon,"dream11","apk","downloads",22.3,"MB","30/2/11",false,true,true),
        File(R.drawable.imageicon,"Screenshot_1122","jpeg","local storage",123.0,"KB","4/4/14",false,true,true),
        File(R.drawable.ppticon,"presentation","pptx","sd card",5.6,"MB","6/12/12",false,true,true),
        File(R.drawable.zipicon,"files_compressed","zip","downloads",1.1,"MB","17/2/17",false,true,true)
    )

    var filePosition = 0

    var actionTitle = "Files"

    var queryString: String = ""

    var isSearched = false

    var moved = false




    fun getList(): LiveData<List<File>>{
        return modifiedList
    }
}