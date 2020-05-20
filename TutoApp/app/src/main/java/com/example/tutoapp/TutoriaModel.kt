package com.example.tutoapp

import java.io.Serializable

class TutoriaModel(
    var id: String,
    var direccion: String,
    var categoria: String,
    var fecha: String,
    var hora: String,
    var nota: String,
    var solicitante: String,
    var tutorSolicitado: String,
var estado : String ,
var nombre_estudiante : String,
var foto_estudiante : String
) : Serializable {
}