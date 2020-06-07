package com.example.tutoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tutoapp.viewmodel.SolicitudViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_ver_solicitud.*


class VerSolicitudActivity : AppCompatActivity() {
    private lateinit var name: TextView
    private lateinit var hora: TextView
    private lateinit var notas: TextView
    private lateinit var fecha: TextView
    private lateinit var materia: TextView
    private lateinit var direccion: TextView
    private lateinit var btnAceptar: Button
    private lateinit var btnRechazar: Button
    private lateinit var imgEstudiante: ImageView
    private lateinit var idEstudiante: String
    private val viewModel by lazy { ViewModelProvider(this).get(SolicitudViewModel::class.java) }

    var toolbar : Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_solicitud)

        initialize()
        observerData()

        toolbar = findViewById(R.id.toolbar)
        toolbar?.setTitle(R.string.ver_solicitud_txt)
        setSupportActionBar(toolbar)

        var actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)


    }

    fun initialize() {
        imgEstudiante = findViewById(R.id.foto_estudiante)
        name = findViewById(R.id.nombre_estudiante)
        fecha = findViewById(R.id.fecha_tutoria)
        direccion = findViewById(R.id.ubicacion_solicitud)
        hora = findViewById(R.id.hora_tutoria)
        notas = findViewById(R.id.notas_tutoria)
        btnAceptar = findViewById(R.id.aceptar_tutoria)
        materia = findViewById(R.id.tv_materia)
        btnRechazar = findViewById(R.id.rechazar_tutoria)
        val solicitud = intent.getSerializableExtra("solicitud") as TutoriaModel

        idEstudiante = solicitud.solicitante
        name.text = solicitud.nombre_estudiante
        hora.text = solicitud.hora
        notas.text = solicitud.nota
        materia.text = solicitud.categoria
        direccion.text = solicitud.direccion
        fecha.text = solicitud.fecha
    }

    private fun observerData() {
        viewModel.getUserSolicitud(idEstudiante).observe(this, Observer {
            //it trae el objeto persona de la base de datos
            Picasso.get().load(it.urlImage).into(imgEstudiante)
        })

    }
}

