package com.example.tutoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_tutor.*

class TutorActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var uid:String

    var toolbar : Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutor)


        nuevo_tutor_action.setOnClickListener {
            startActivity(Intent(this,AtributesActivity::class.java))
            finish()
        }

        toolbar = findViewById(R.id.toolbar)
        toolbar?.setTitle("")
        setSupportActionBar(toolbar)

        var actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

    }
}
