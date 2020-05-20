package com.example.tutoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tutoapp.TutoriaModel
import com.example.tutoapp.domain.data.network.Repo

class TutorViewModel: ViewModel() {
    val repo = Repo()

    fun postUserData (tutoria: TutoriaModel,idTutor: String): Int
    {

        return repo.postUserData(tutoria,idTutor)
    }
    fun getUserSolicitud(idTutor: String): LiveData<MutableList<TutoriaModel>> {
        val mutableData = MutableLiveData<MutableList<TutoriaModel>>()
        repo.getUserSolicitud(idTutor).observeForever { solicitudes->
            mutableData.value = solicitudes
        }

        return mutableData
    }

}