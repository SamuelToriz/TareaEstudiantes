package com.example.estudiantessamuel

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.estudiantessamuel.Data.ListStudent
import com.example.estudiantessamuel.Entity.EntityStudent
import com.example.estudiantessamuel.Tools.Constants
import com.example.estudiantessamuel.databinding.ActivityListStudentBinding

class ListActivityStudent : AppCompatActivity()
{
    private lateinit var binding : ActivityListStudentBinding
    private val listStudent = ListStudent()


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityListStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setTitle("Lista para Editar o Eliminar")
        listStudent.showListStudents()

        val adapter = ArrayAdapter<String>(this@ListActivityStudent, R.layout.simple_list_item_1, listStudent.getStringArray())
        binding.ltvListStudents.adapter = adapter

        binding.ltvListStudents.setOnItemClickListener{ adapterView: AdapterView<*>, view1: View, position: Int, id: Long ->

            val student = listStudent.getStudent(position)
            miDialogo(position, student.name).show()
            
        }


    }

    override fun onRestart() {
        super.onRestart()
        val adapter = ArrayAdapter<String>(this@ListActivityStudent, android.R.layout.simple_list_item_1, listStudent.getStringArray())
        binding.ltvListStudents.adapter = adapter

  //      listStudent.showListStudents()
    }

    private fun miDialogo(position : Int, name : String): AlertDialog
    {
        val miAlerta = AlertDialog.Builder(this@ListActivityStudent)
        miAlerta.setTitle("Editar o Eliminar")
        miAlerta.setMessage("Desea Editar o Eliminar alumno?")

        miAlerta.setPositiveButton("Eliminar"){_,_ ->
           var answer = listStudent.delete(name)
            if(answer == true)
           {
               Toast.makeText(this@ListActivityStudent, "Estudiante eliminado", Toast.LENGTH_SHORT).show()
           }
            else
           {
               Toast.makeText(this@ListActivityStudent, "Estudiante NO eliminado", Toast.LENGTH_SHORT).show()
           }
        }
        miAlerta.setNegativeButton("Editar"){_,_ ->

               val intent = Intent(this@ListActivityStudent, Edit_DeleteActivity::class.java).apply {

                   putExtra(Constants.ID, position)
                }
               startActivity(intent)
        }

        return miAlerta.create()
    }
}