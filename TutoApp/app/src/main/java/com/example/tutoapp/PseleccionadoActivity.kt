package com.example.tutoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_pseleccionado.*

class PseleccionadoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pseleccionado)
        val tutor=intent.getSerializableExtra("tutor") as Model

        nombre_tutor.text= tutor.tittle
        descripcion_tutor.text=tutor.description
        image_tutor.setImageResource(tutor.img)
    }
}
