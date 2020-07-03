package com.example.tutoapp.models

import java.io.Serializable

class Model(
    var id: String,
    var name:String,
    var lastname: String,
    var correo: String,
    var telefono: String,
    var nivel: String,
    var ocupacion: String,
    var location: String,
    var img: Int,
    var ruta: String = "",
    var descripcion: String,
    var listaDisciplina: ArrayList<Disciplina>?
): Serializable{
    constructor(): this ("","","","","","","","",0,"","",null)
}