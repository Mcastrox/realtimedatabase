package com.example.tutoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class SolicitudActivity : AppCompatActivity() {
private lateinit var txt_direccion : EditText
    private lateinit var txt_categoria : EditText
    private lateinit var txt_fecha : EditText
    private lateinit var txt_hora : EditText
    private lateinit var txt_notas : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solicitud)

        val solicitud=intent.getSerializableExtra("solicitud") as TutoriaModel

        val id = solicitud.id

        txt_direccion=findViewById(R.id.txt_direccion)
        txt_categoria=findViewById(R.id.txt_categoria)
        txt_fecha = findViewById(R.id.txt_fecha)
        txt_hora =findViewById(R.id.txt_hora)
        txt_notas =findViewById(R.id.notas_tutor)

    }
}
