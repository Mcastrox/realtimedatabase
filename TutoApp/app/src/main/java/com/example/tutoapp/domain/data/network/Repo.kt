package com.example.tutoapp.domain.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tutoapp.TutoriaModel
import com.google.firebase.database.MutableData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class Repo {
    fun postUserData(tutoria: TutoriaModel, idTutor: String): Int {
        var db = FirebaseFirestore.getInstance().collection("Usuarios")
        db.document(idTutor).collection("Solicitudes").document(tutoria.id).set(tutoria)
        return 1
    }
    fun getUserSolicitud(idTutor: String) : LiveData<MutableList<TutoriaModel>>{

        val mutableData = MutableLiveData<MutableList<TutoriaModel>>()

        var db = FirebaseFirestore.getInstance().collection("Usuarios")
            .document(idTutor).collection("Solicitudes")

        db.get().addOnSuccessListener { result->
            val listData : MutableList<TutoriaModel> = mutableListOf<TutoriaModel>()
            for(document in result){
                val id:String ? = document.getString("id")
                val direccion:String ? = document.getString("direccion")
                val categoria:String ? = document.getString("categoria")
                val fecha:String ? = document.getString("fecha")
                val hora:String ? = document.getString("hora")
                val nota:String ? = document.getString("nota")
                val solicitante:String ? = document.getString("solicitante")
                val tutorSolicitado:String ? = document.getString("tutorSolicitado")
                val estado: String? = document.getString("estado")
                val nombre_estudiante : String ? =document.getString("nombre_estudiante")
                val foto_estudiante : String? =document.getString("foto_estudiante")
                val solicitud = TutoriaModel(id!!,direccion!!,categoria!!,fecha!!,hora!!,nota!!,solicitante!!,tutorSolicitado!!,estado!!,nombre_estudiante!!,foto_estudiante!!)
                listData.add(solicitud)
            }
            mutableData.value = listData
        }

        return  mutableData
    }
}

