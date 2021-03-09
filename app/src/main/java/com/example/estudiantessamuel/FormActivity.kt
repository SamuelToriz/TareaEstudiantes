package com.example.estudiantessamuel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.view.isNotEmpty
import com.example.estudiantessamuel.Data.ListStudent
import com.example.estudiantessamuel.Entity.EntityStudent
import com.example.estudiantessamuel.Tools.Constants
import com.example.estudiantessamuel.databinding.ActivityDetailBinding
import com.example.estudiantessamuel.databinding.ActivityFormBinding
import com.google.android.material.snackbar.Snackbar

class FormActivity : AppCompatActivity()
{

    private lateinit var binding : ActivityFormBinding
    private val listStudent = ListStudent()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ///Funcionalidades ActionBar
        supportActionBar?.setTitle(R.string.text_home)

        binding.btnOk.setOnClickListener()
        {

            if(binding.editTextPersonName.text.isNotEmpty() && binding.editTextPersonLastName.text.isNotEmpty() && binding.spnGender.selectedItemPosition!=0
                    && binding.rgbDegree.checkedRadioButtonId!=-1  ){

                val student = EntityStudent()

                student.name = binding.editTextPersonName.text.toString()
                student.lastName = binding.editTextPersonLastName.text.toString()
                student.gender = binding.spnGender.selectedItemPosition
                //val genderText : String = binding.spnGender.selectedItem.toString()

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

                val index = listStudent.add(student)

                if (index >= 0)
                {
                    Toast.makeText(this@FormActivity, "Estudiante guardado", Toast.LENGTH_SHORT).show()
                    cleanControls()

                } else {
                    Snackbar.make(it, "Estudiante NO guardado ", Snackbar.LENGTH_LONG).show()
                }

            }
            else
            {
                Snackbar.make(it, "Todos los campos son OBLIGATORIOS excepto Beca y Hobbies ", Snackbar.LENGTH_LONG).show()
            }


        }

/*
        binding.spnGender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener
        {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(this@FormActivity, "Estas en evento ON NOTHING SELECTED", Toast.LENGTH_SHORT).show()
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long)
            {

                val selectedItem = parent?.getItemAtPosition(position)
                Toast.makeText(this@FormActivity, "Estas en evento  ON ITEM SELECTED $position $selectedItem", Toast.LENGTH_SHORT).show()
            }


        }

        binding.stwFinancialAssistance.setOnCheckedChangeListener{it,isChecked ->
            val checked = if (isChecked) "On" else "Off"

            Toast.makeText(this@FormActivity, "Estas en evento setOnCheckedChangeListener $checked", Toast.LENGTH_SHORT).show()
        }
*/

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_form, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){

            R.id.itmList->{
                val intent = Intent(this@FormActivity, ListActivity::class.java)
                startActivity(intent)
            }

            R.id.itmForm->{
                val intent = Intent(this@FormActivity, ListActivityStudent::class.java)
                startActivity(intent)
            }

            R.id.itmExit->{
                finish()
            }


        }

        return super.onOptionsItemSelected(item)
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