package com.example.estudiantessamuel.Entity

class EntityStudent(
    var name:String,
    var lastName:String,
    var gender:Int,
    var degree:String,
    var reading:Boolean,
    var sport:Boolean,
    var travel:Boolean,
    var financialAid:Boolean) {

    constructor():this("", "", 0,"", false, false, false,false)
}

