package com.example.tutoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tutoapp.models.TutoriaModel
import com.example.tutoapp.domain.data.network.Repo

class TutorViewModel : ViewModel() {
    private val repo = Repo()

    fun postUserData(tutoria: TutoriaModel, idTutor: String, idEstudiante: String): Int {

        return repo.postUserData(tutoria, idTutor, idEstudiante)
    }

    fun getUserSolicitud(idTutor: String): LiveData<MutableList<TutoriaModel>> {
        val mutableData = MutableLiveData<MutableList<TutoriaModel>>()
        repo.getUserSolicitud(idTutor).observeForever { solicitudes ->
            mutableData.value = solicitudes
        }

        return mutableData
    }

    fun getEstudianteSolicitud(idEstudiante: String): LiveData<MutableList<TutoriaModel>> {
        val mutableData = MutableLiveData<MutableList<TutoriaModel>>()
        repo.getStudentUserSolicitud(idEstudiante).observeForever { solicitudes->
            mutableData.value = solicitudes
        }

        return mutableData
    }

    fun updateEstadoSolicitud(idTutor: String, idEstudiante: String, idSolicitud: String, nuevoEstado: String){
        return repo.updateSolicitud(idTutor,idEstudiante,idSolicitud,nuevoEstado)
    }
}