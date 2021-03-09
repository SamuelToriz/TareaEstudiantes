package com.example.estudiantessamuel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.estudiantessamuel.Data.ListStudent
import com.example.estudiantessamuel.Tools.Constants
import com.example.estudiantessamuel.databinding.ActivityFormBinding
import com.example.estudiantessamuel.databinding.ActivityListBinding

class ListActivity : AppCompatActivity() {

    private lateinit var binding : ActivityListBinding
    private val listStudent = ListStudent()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setTitle(R.string.text_list)

        listStudent.showListStudents()


        val adapter = ArrayAdapter<String>(this@ListActivity, android.R.layout.simple_list_item_1, listStudent.getStringArray())
        binding.ltvStudents.adapter = adapter

        binding.ltvStudents.setOnItemClickListener{ adapterView: AdapterView<*>, view1: View, position: Int, id: Long ->

            val selectedItem = adapterView.getItemAtPosition(position)

            Toast.makeText(this@ListActivity, "$position $id $selectedItem", Toast.LENGTH_SHORT).show()

            val intent = Intent(this@ListActivity, DetailActivity::class.java).apply {

                //pasar datos al siguiente activity
                putExtra(Constants.ID, position)
            }

            startActivity(intent)
        }

    }

    override fun onRestart() {
        super.onRestart()
        val adapter = ArrayAdapter<String>(this@ListActivity, android.R.layout.simple_list_item_1, listStudent.getStringArray())
        binding.ltvStudents.adapter = adapter
    }


}