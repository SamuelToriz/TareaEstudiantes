package com.example.estudiantessamuel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.estudiantessamuel.Data.ListStudent
import com.example.estudiantessamuel.Tools.Constants
import com.example.estudiantessamuel.databinding.ActivityListBinding
import com.example.estudiantessamuel.databinding.ActivityViiewAdapterBinding

class ViiewAdapterActivity : AppCompatActivity()
{
    private lateinit var binding : ActivityViiewAdapterBinding
    private val listStudent = ListStudent()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityViiewAdapterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setTitle("View info Adapater")

        val id:Int = intent.getIntExtra(Constants.ID, -1)
        if(id>-1)
        {
            val student = listStudent.getStudent(id)
            binding.txtvFullName.text = "${student.name} ${student.lastName}"
            binding.txtvDegree.text = "Grado de estudios ${student.degree}"
            binding.txtvGender.text = "Sexp: ${if(student.gender==1) " Masculino" else if(student.gender==2) " Femenino" else " Genero no seleccionado"}"
            binding.txtvFinancialAid.text = "Estudiante: ${if(student.financialAid) "Tiene beca" else "No tiene beca"}"
            binding.txtvHobbies.text = "Hobbies del estudiante: ${if(student.sport) "Deportes" else ""} ${if(student.reading) "Leer" else ""} ${if(student.travel) "Viajar" else ""}"
        }
        else
        {
            Toast.makeText(this@ViiewAdapterActivity, "Se genero un problema al cargar esta actividad", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}