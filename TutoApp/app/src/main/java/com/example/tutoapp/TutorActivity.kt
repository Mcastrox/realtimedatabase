package com.example.tutoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_tutor.*

class TutorActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var uid:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutor)

        auth = FirebaseAuth.getInstance()
        val user: FirebaseUser?=auth.currentUser
        uid = user?.uid!!
        val referencia =  FirebaseDatabase .getInstance (). getReference ( "Users" ) .child (uid)
        nuevo_tutor_action.setOnClickListener {
            referencia.child("Rol").setValue("Tutor")
            startActivity(Intent(this,AtributesActivity::class.java))
            finish()
        }
    }
}
