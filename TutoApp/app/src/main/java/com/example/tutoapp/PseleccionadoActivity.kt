package com.example.tutoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_pseleccionado.*

class PseleccionadoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pseleccionado)
        val tutor=intent.getSerializableExtra("tutor") as Model

        nombre_tutor.text= tutor.tittle
        descripcion_tutor.text=tutor.description
        Picasso.get().load(tutor.ruta).into(image_tutor)


        action_contactar.setOnClickListener {
            startActivity(Intent(this,SolicitudActivity::class.java))
        }
    }
}
