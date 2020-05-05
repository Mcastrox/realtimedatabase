package com.example.tutoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        categories.setOnClickListener{
            startActivity(Intent(this,CategoriesActivity::class.java))
        }
        profile.setOnClickListener {
            startActivity(Intent(this,MperfilActivity::class.java))
        }
        search.setOnClickListener {
            startActivity(Intent(this,MostrarActivity::class.java))
        }

    }

}
