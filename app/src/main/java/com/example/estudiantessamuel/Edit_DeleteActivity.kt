package com.example.estudiantessamuel

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
import com.example.estudiantessamuel.databinding.ActivityEditDeleteBinding
import com.example.estudiantessamuel.databinding.ActivityListBinding
import com.google.android.material.snackbar.Snackbar

class Edit_DeleteActivity : AppCompatActivity()
{
    private lateinit var binding : ActivityEditDeleteBinding
    private val listStudent = ListStudent()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityEditDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setTitle("Lista de Estudiantes")

        var id:Int = intent.getIntExtra(Constants.ID, -1)

        if(id!=1)
        {
            var student = listStudent.getStudent(id)

            binding.editTextPersonName.setText(student.name)
            binding.editTextPersonLastName.setText(student.lastName)
            binding.spnGender.setSelection(student.gender)
            binding.stwFinancialAssistance.isChecked = student.financialAid
            binding.ckbRead.isChecked = student.reading
            binding.ckbSport.isChecked = student.sport
            binding.ckbTravel.isChecked = student.travel

            when (student.degree)
            {
                "Trunco" ->
                {
                    binding.rgbDegree.check(binding.rdbUnfinishedStudies.id)
                }
                "Pasante" -> {
                    binding.rgbDegree.check(binding.rdbUniversityIntern.id)
                }
                "Titulado" -> {
                    binding.rgbDegree.check(binding.rdbFinishedStudies.id)
                }
            }


            binding.btnSave.setOnClickListener {
                miDialogo(student.name).show()
                if(binding.editTextPersonName.text.isNotEmpty() && binding.editTextPersonLastName.text.isNotEmpty() && binding.spnGender.selectedItemPosition!=0
                        && binding.rgbDegree.checkedRadioButtonId!=-1  ){

                    val student = EntityStudent()

                    student.name = binding.editTextPersonName.text.toString()
                    student.lastName = binding.editTextPersonLastName.text.toString()
                    student.gender = binding.spnGender.selectedItemPosition


                    //Para los radioButon
                    when (binding.rgbDegree.checkedRadioButtonId) {
                        binding.rdbUnfinishedStudies.id -> {
                            student.degree = "Trunco"
                        }
                        binding.rdbUniversityIntern.id -> {
                            student.degree = "Pasante"
                        }
                        binding.rdbFinishedStudies.id -> {
                            student.degree = "Titulado"
                        }
                    }

                    ///Para checkBox
                    student.sport = binding.ckbSport.isChecked
                    student.travel = binding.ckbTravel.isChecked
                    student.reading = binding.ckbRead.isChecked

                    //para el switch
                    student.financialAid = binding.stwFinancialAssistance.isChecked

                    val index = listStudent.edit(id,student)
/*
                    if (index >= 0)
                    {
                        Toast.makeText(this@Edit_DeleteActivity, "Estudiante guardado", Toast.LENGTH_SHORT).show()
                        cleanControls()

                    } else {
                        Snackbar.make(it, "Estudiante NO guardado ", Snackbar.LENGTH_LONG).show()
                    }
*/
                }
                else
                {
                    Snackbar.make(it, "Todos los campos son OBLIGATORIOS excepto Beca y Hobbies ", Snackbar.LENGTH_LONG).show()
                }

            }
        }

    }

    private fun miDialogo(name : String): AlertDialog
    {
        var id:Int = intent.getIntExtra(Constants.ID, -1)
        var student = listStudent.getStudent(id)
        val miAlerta = AlertDialog.Builder(this@Edit_DeleteActivity)
        miAlerta.setTitle("Confirmar")
        miAlerta.setMessage("Esta seguro??")

        Toast.makeText(this@Edit_DeleteActivity, "$id", Toast.LENGTH_SHORT).show()
        miAlerta.setPositiveButton("Si"){_,_ ->
            var answer = listStudent.edit(id, student)

            if(answer==true)
            {
                Toast.makeText(this@Edit_DeleteActivity, "Estudiante ha sido editado", Toast.LENGTH_SHORT).show()
                cleanControls()
                val intent = Intent(this@Edit_DeleteActivity, ListActivityStudent::class.java)
                startActivity(intent)
                finish()
            }
            else
            {
                Toast.makeText(this@Edit_DeleteActivity, "Estudiante NO editado", Toast.LENGTH_SHORT).show()
            }

        }
        miAlerta.setNegativeButton("No") {_,_ ->
            Toast.makeText(this@Edit_DeleteActivity, "Edicion Cancelada", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@Edit_DeleteActivity, ListActivityStudent::class.java)
            startActivity(intent)
        }

        return miAlerta.create()
    }

    fun cleanControls()
    {
        binding.editTextPersonName.setText("")
        binding.editTextPersonLastName.setText("")
        binding.spnGender.setSelection(0)
        binding.rgbDegree.clearCheck()
        binding.ckbSport.isChecked = false
        binding.ckbRead.isChecked = false
        binding.ckbTravel.isChecked = false
        binding.stwFinancialAssistance.isChecked = false
    }
}