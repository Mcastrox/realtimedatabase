package com.example.tutoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tutoapp.models.Persona
import com.example.tutoapp.domain.data.network.Repo

class SolicitudViewModel: ViewModel() {
    val repo = Repo()


    fun getUserSolicitud(idSolicitante: String): LiveData<Persona> {
        val mutableData = MutableLiveData<Persona>()

        repo.getUsuario(idSolicitante).observeForever { solicitante ->
            mutableData.value = solicitante
        }
        return mutableData
    }
}
