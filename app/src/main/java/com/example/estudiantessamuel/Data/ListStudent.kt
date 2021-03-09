package com.example.estudiantessamuel.Data

import android.provider.SyncStateContract
import android.util.Log
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.Constraints
import com.example.estudiantessamuel.Entity.EntityStudent
import com.example.estudiantessamuel.Tools.Constants
import org.w3c.dom.Entity

class ListStudent {



    fun add(student: EntityStudent):Int
    {
        var answer=-1
        if(!existNameFilter(student.name))
        {
            listStudent.add(student)
            answer = listStudent.size -1
        }

        return answer
    }


    fun delete(name: String):Boolean
    {
        var answer : Boolean = false

        for((index, item) in listStudent.withIndex())
        {
            if(item.name == name)
            {
                listStudent.removeAt(index)
                answer = true
                break
            }
        }
        return answer
    }


    fun edit(position : Int, student : EntityStudent):Boolean
    {
        var answer : Boolean = false

            if(position>-1)
            {
                listStudent[position].name= student.name
                listStudent[position].financialAid = student.financialAid
                listStudent[position].gender = student.gender
                listStudent[position].degree = student.degree
                listStudent[position].lastName = student.lastName
                listStudent[position].sport = student.sport
                listStudent[position].travel = student.travel
                listStudent[position].reading = student.reading

                answer = true
            }

        return answer
    }

    fun showListStudents()
    {
        Log.d(Constants.LOG_TAG, "${listStudent.size}")

        for((index, item) in listStudent.withIndex())
        {
            //Log.d(Constants.LOG_TAG)
            Log.d(Constants.LOG_TAG, "$index ${listStudent[index].name}, ${listStudent[index].degree}, ${listStudent[index].gender}")
        }

    }


    fun existsName(name:String): Boolean
    {
        var answer: Boolean = false

        for(element in listStudent)
        {
            if(element.name == name)
            {
                answer = true
                break
            }
        }
        return answer
    }

    fun existNameFilter(name:String): Boolean
    {
        var answer: Boolean = false

        if(listStudent.filter { e-> e.name == name }.count()==1)
        {
            answer=true
        }

        return answer
    }

    fun getStringArray():Array<String>
    {
        val answerList = arrayListOf<String>()
        for((index, item)in listStudent.withIndex())
        {
            answerList.add("${item.name}, ${item.lastName}")
        }
        return answerList.toTypedArray()
    }


    fun getEntityStudent():Array<EntityStudent>
    {
        return listStudent.toTypedArray()
    }

    fun getStudent(index:Int):EntityStudent
    {

        return listStudent[index]
    }



    companion object
    {
        private var listStudent = arrayListOf<EntityStudent>()
    }

}