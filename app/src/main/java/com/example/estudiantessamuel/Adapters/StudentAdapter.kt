package com.example.estudiantessamuel.Adapters

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.estudiantessamuel.Data.ListStudent
import com.example.estudiantessamuel.EditAdapterActivity
import com.example.estudiantessamuel.Entity.EntityStudent
import com.example.estudiantessamuel.R
import com.example.estudiantessamuel.Tools.Constants
import com.example.estudiantessamuel.ViiewAdapterActivity
import com.example.estudiantessamuel.databinding.ActivityEditDeleteBinding
import com.example.estudiantessamuel.databinding.ItemStudentBinding
import com.google.android.material.snackbar.Snackbar

///Necesita lista de los elementos a mostrar y el contexto y hereda de RecyclerView
class StudentAdapter(val studentList : ArrayList<EntityStudent>, val context:Context) : RecyclerView.Adapter<StudentHolder>()
{
    private lateinit var binding : StudentAdapter
    private val listStudent = ListStudent()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder
    {
        val inflater =  LayoutInflater.from(parent.context)
        return StudentHolder(inflater.inflate(R.layout.item_student, parent, false))
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: StudentHolder, position: Int)
    {
        holder.txtvFullName.text = "${studentList[position].name} ${studentList[position].lastName}"
        holder.txtvGender.text = if(studentList[position].gender==1) "Masculino" else "Femenino"
        holder.txtvDegree.text = studentList[position].degree

        holder.btnDelete.setOnClickListener{

        miDialogo(studentList[position].name, it).show()

        }

        holder.btnEdit.setOnClickListener{
            val intent = Intent(context, EditAdapterActivity::class.java).apply {
                putExtra(Constants.ID, position)
            }
            startActivity(context,intent,null)
            //context.startActivity(intent)
        }

        holder.btnView.setOnClickListener{

            val intent = Intent(context, ViiewAdapterActivity::class.java).apply {
                putExtra(Constants.ID, position)
            }
            context.startActivity(intent)
        }
    }

    private fun miDialogo(name:String, view:View) : AlertDialog
    {
        val myAlert = AlertDialog.Builder(context)
        myAlert.setTitle("Estudiante Samuel")
        myAlert.setIcon(R.drawable.leon)
        myAlert.setMessage("Desea eliminar el estudiante seleccionado?")
        myAlert.setPositiveButton("Si"){ dialogInterface: DialogInterface, i: Int ->

            val listStudent = ListStudent()
            if(listStudent.delete(name)  )
            {
                notifyDataSetChanged()
                Toast.makeText(context, "Estudiante Eliminado", Toast.LENGTH_SHORT).show()
            }
            else
            {
               Snackbar.make(view, "No se elimino el estudiante", Snackbar.LENGTH_SHORT).show()
            }

        }
        myAlert.setNegativeButton("No"){ dialogInterface: DialogInterface, i: Int ->

        }

        return myAlert.create()
    }

}

class StudentHolder(view:View): RecyclerView.ViewHolder(view)
{
    val binding = ItemStudentBinding.bind(view)

    val imgLogo = binding.imgLogo
    val txtvFullName = binding.txtvFullName
    val txtvGender = binding.txtvGender
    val txtvDegree = binding.txtvDegree
    val btnDelete = binding.btnDelete
    val btnEdit = binding.btnEdit
    val btnView = binding.btnView

}