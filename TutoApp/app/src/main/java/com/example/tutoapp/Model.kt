package com.example.tutoapp

import java.io.Serializable

class Model(var id : String ,var tittle:String,var description : String, var img : Int , var ruta: String = "" ): Serializable{
}