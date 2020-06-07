package com.example.tutoapp.domain.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tutoapp.Persona
import com.example.tutoapp.TutoriaModel
import com.google.firebase.database.*
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
                val apellido_estudiante : String ? = document.getString("apellido_estudiante")
                val foto_estudiante : String? =document.getString("foto_estudiante")
                val solicitud = TutoriaModel(id!!,direccion!!,categoria!!,fecha!!,hora!!,nota!!,solicitante!!,tutorSolicitado!!,estado!!,nombre_estudiante!!,foto_estudiante!!,apellido_estudiante!!)
                listData.add(solicitud)
            }
            mutableData.value = listData
        }

        return  mutableData
    }

    fun getUsuario (idUser: String ): LiveData<Persona>{
        val ref = FirebaseDatabase.getInstance().getReference("Users").child(idUser)
        var persona = MutableLiveData<Persona>()
        val postListener = object : ValueEventListener {
            override fun onCancelled(dataSnapshot: DatabaseError) {

            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                var id:String = ""
                var name:String = ""
                var correo:String = ""
                var direccion:String = ""
                var lastName:String = ""
                var telefono:String = ""
                var urlImage:String = ""

                if (dataSnapshot.child("ID").value != null) {
                    id = dataSnapshot.child("ID").value as String
                }

                if (dataSnapshot.child("Name").value != null) {
                    name = dataSnapshot.child("Name").value as String
                }

                if (dataSnapshot.child("correo").value != null) {
                    correo = dataSnapshot.child("correo").value as String
                }

                if (dataSnapshot.child("direccion").value != null) {
                    direccion = dataSnapshot.child("direccion").value as String
                }
                if (dataSnapshot.child("telefono").value != null) {
                    telefono = dataSnapshot.child("telefono").value as String
                }

                if (dataSnapshot.child("urlImage").value != null) {
                    urlImage = dataSnapshot.child("urlImage").value as String
                }

                //aca guardamos el objeto que enviaremos
                persona.value = Persona(id,name,correo,direccion,lastName,telefono,urlImage)
            }

        }
        ref.addListenerForSingleValueEvent(postListener)

        return persona
    }

}

