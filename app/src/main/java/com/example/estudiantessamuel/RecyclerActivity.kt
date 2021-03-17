package com.example.estudiantessamuel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.estudiantessamuel.Adapters.StudentAdapter
import com.example.estudiantessamuel.Data.ListStudent
import com.example.estudiantessamuel.databinding.ActivityFormBinding
import com.example.estudiantessamuel.databinding.ActivityRecyclerBinding

class RecyclerActivity : AppCompatActivity()
{
    private lateinit var binding : ActivityRecyclerBinding
    private val listStudent = ListStudent()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setTitle(R.string.text_recycler)

        val list = listStudent.getEntityStudentArray()
        val adapter = StudentAdapter(list, this@RecyclerActivity)
        val linearLayout = LinearLayoutManager(this@RecyclerActivity, LinearLayoutManager.VERTICAL, false)

        binding.rvwStudents.layoutManager = linearLayout
        binding.rvwStudents.setHasFixedSize(true)
        binding.rvwStudents.adapter = adapter

    }
}