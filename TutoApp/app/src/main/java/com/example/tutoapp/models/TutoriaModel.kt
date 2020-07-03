package com.example.tutoapp.models

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
    var estado: String,
    var nombre_estudiante: String,
    var foto_estudiante: String,
    var apellido_estudiante: String,
    var nombre_tutor : String,
    var apellido_tutor : String,
    var foto_tutor: String,
    var correoTutor : String,
    var telefonoTutor: String
) : Serializable {
}