package com.example.tutoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_pseleccionado.*

class PseleccionadoActivity : AppCompatActivity() {
        private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pseleccionado)
        auth = FirebaseAuth.getInstance()
        val user: FirebaseUser?=auth.currentUser

        val tutor=intent.getSerializableExtra("tutor") as Model
        nombre_tutor.text= tutor.tittle
        descripcion_tutor.text=tutor.description
        Picasso.get().load(tutor.ruta).into(image_tutor)


        action_contactar.setOnClickListener {
            val intent = Intent(this,SolicitudActivity::class.java)
            intent.putExtra("solicitud", user?.uid!!)
            //startActivity(Intent(this,SolicitudActivity::class.java))
            startActivity(intent)
        }
    }
}
