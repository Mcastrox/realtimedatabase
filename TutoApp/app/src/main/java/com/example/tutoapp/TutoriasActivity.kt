package com.example.tutoapp

import android.content.ClipData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.get

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tutoapp.viewmodel.TutorViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_tutorias.*
import kotlinx.android.synthetic.main.fragment_search.*

class TutoriasActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var uidTutor: String

    private lateinit var adapter: SolicitudAdapter

    private val viewModel by lazy { ViewModelProvider(this).get(TutorViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorias)
        getCurrentUser()
        observerData()

        lista_solicitudes.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this,VerSolicitudActivity::class.java)
            intent.putExtra("solicitud", lista_solicitudes[position].toString())
            startActivity(intent)
        }

    }

    private fun getCurrentUser() {
        auth = FirebaseAuth.getInstance()
        val user: FirebaseUser? = auth.currentUser
        uidTutor = user?.uid!!
    }

    private fun observerData() {
        viewModel.getUserSolicitud(uidTutor).observe(this, Observer {
            adapter = SolicitudAdapter(this, it)
            lista_solicitudes.adapter = adapter
        })
    }
}
