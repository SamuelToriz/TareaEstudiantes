package com.example.estudiantessamuel.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.estudiantessamuel.Entity.EntityStudent
import com.example.estudiantessamuel.R
import com.example.estudiantessamuel.databinding.ItemStudentBinding

///Necesita lista de los elementos a mostrar y el contexto y hereda de RecyclerView
class StudentAdapter(val studentList : Array<EntityStudent>, val context:Context) : RecyclerView.Adapter<StudentHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        val inflater =  LayoutInflater.from(parent.context)
        return StudentHolder(inflater.inflate(R.layout.item_student, parent, false))
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: StudentHolder, position: Int) {
        holder.txtvFullName.text = "${studentList[position].name} ${studentList[position].lastName}"
        holder.txtvGender.text = if(studentList[position].gender==1) "Masculino" else "Femenino"
        holder.txtvDegree.text = studentList[position].degree

        holder.btnDelete.setOnClickListener{
            Toast.makeText(context, "Estoy en DELETE", Toast.LENGTH_SHORT).show()
        }
        holder.btnEdit.setOnClickListener{
            Toast.makeText(context, "Estoy en EDIT", Toast.LENGTH_SHORT).show()
        }
        holder.btnView.setOnClickListener{
            Toast.makeText(context, "Estoy en VIEW", Toast.LENGTH_SHORT).show()
        }
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