package com.example.tutoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class VerSolicitudActivity : AppCompatActivity() {
private lateinit var name: TextView
    private lateinit var  hora : TextView
    private lateinit var notas: TextView
    private lateinit var btnAceptar : Button
    private lateinit var btnRechazar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_solicitud)
        initialize()
       /* val solicitud =intent.getSerializableExtra("solicitud") as TutoriaModel*/



    }
    fun initialize(){
        name = findViewById(R.id.nombre_estudiante)
        hora = findViewById(R.id.hora_tutoria)
        notas = findViewById(R.id.notas_tutoria)
        btnAceptar =findViewById(R.id.aceptar_tutoria)
        btnRechazar = findViewById( R.id.rechazar_tutoria)
    }
}
