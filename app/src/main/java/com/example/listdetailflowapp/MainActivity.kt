package com.example.listdetailflowapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.example.listdetailflowapp.dataclass.File
import com.example.listdetailflowapp.viewmodel.FilesViewModel
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: FilesViewModel

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProvider(this)[FilesViewModel::class.java]

        supportFragmentManager.commit {
            replace(R.id.mainFragment, MainFragment())
            addToBackStack("main")
        }

        val navView = findViewById<NavigationView>(R.id.navView)

        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> {
                    println("Home")
                    supportFragmentManager?.commit {
                        replace(R.id.mainFragment, MainFragment())
                    }
                    drawerLayout.closeDrawers()
                }
                R.id.settings -> {
                    println("settings")
                    supportFragmentManager?.commit {
                        replace(R.id.mainFragment, SettingsFragment())
                        viewModel.position.value = -1
                    }
                    drawerLayout.closeDrawers()
                }
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val list = viewModel.fileList
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        println(item.title)
        return when(item.itemId){
            R.id.allFiles -> {
                modifyList(0, list)
                true
            }
            R.id.pdf -> {
                modifyList(1,list)
                true
            }
            R.id.image -> {
                modifyList(2,list)
                true
            }
            R.id.apk -> {
                modifyList(3,list)
                true
            }
            R.id.txt -> {
                modifyList(4,list)
                true
            }
            R.id.doc -> {
                modifyList(5,list)
                true
            }
            R.id.ppt -> {
                modifyList(6,list)
                true
            }
            R.id.zip -> {
                modifyList(7,list)
                true
            }
            R.id.search -> {
                println("Search clicked")
                true
            }
            else ->{
                super.onOptionsItemSelected(item)
            }
        }
    }

    fun modifyList(position: Int, files: List<File>){

        when(position){
            0 -> {
                viewModel.modifiedList.value = files
            }
            1 -> {
                viewModel.modifiedList.value = files.filter {
                    it.fileExtension == "pdf"
                }
            }
            2 -> {
                viewModel.modifiedList.value = files.filter {
                    it.fileExtension == "jpg" || it.fileExtension == "jpeg"
                }
            }
            3 -> {
                viewModel.modifiedList.value = files.filter {
                    it.fileExtension == "apk"
                }
            }
            4 -> {
                viewModel.modifiedList.value = files.filter {
                    it.fileExtension == "txt"
                }
            }
            5 -> {
                viewModel.modifiedList.value = files.filter {
                    it.fileExtension == "doc"
                }
            }
            6 -> {
                viewModel.modifiedList.value = files.filter {
                    it.fileExtension == "pptx"
                }
            }
            7 -> {
                viewModel.modifiedList.value = files.filter {
                    it.fileExtension == "zip"
                }
            }
        }
    }

    override fun onBackPressed() {

        val fragmentInstance = supportFragmentManager.findFragmentById(R.id.mainFragment)

        if(fragmentInstance is MainFragment && viewModel.position.value == 0 && viewModel.modifiedList.value == viewModel.fileList){
            finish()
        }

        supportFragmentManager.commit {
            addToBackStack(null)
            replace(R.id.mainFragment, MainFragment())
        }
//        supportFragmentManager.restoreBackStack("main")
    }
}