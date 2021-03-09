package com.example.estudiantessamuel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import android.widget.Toast
import com.example.estudiantessamuel.Data.ListStudent
import com.example.estudiantessamuel.Tools.Constants
import com.example.estudiantessamuel.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.Snackbar

class DetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailBinding
    private val listStudent = ListStudent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setTitle(R.string.text_detail)

        val id:Int = intent.getIntExtra(Constants.ID, -1)

        if(id!=-1)
        {
            val student = listStudent.getStudent(id)

            binding.txtvFullName.text = "${student.name} ${student.lastName}"
            binding.txtvGender.text = "${if(student.gender==1) "Masculino" else if(student.gender==2) "Femenino" else "Genero no seleccionado"}"
            binding.txtvDegree.text = "${student.degree}"
            binding.txtvHobbies.text = "Pasatiempos: ${if(student.sport) "Deportes" else ""} ${if(student.reading) "Leer" else ""} ${if(student.travel) "Viajar" else ""}"
            binding.txtvFinancialAid.text = "${if(student.financialAid) "Con beca" else "Sin beca"}"

 /*           binding.btnDelete.setOnClickListener {

                if( listStudent.delete(student.name))
               {
                   Toast.makeText(this@DetailActivity, "Estudiante ha sido eliminado", Toast.LENGTH_SHORT).show()
                   finish()
               }
                else
               {
                   Toast.makeText(this@DetailActivity, "Estudiante NO eliminado", Toast.LENGTH_SHORT).show()
               }
            }
*/
        }
        else
        {
            Toast.makeText(this@DetailActivity, "Se genero un problema al cargar esta actividad", Toast.LENGTH_SHORT).show()
            finish()
        }

    }
}