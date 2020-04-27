package com.example.tarea

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tarea.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),SecondFragment.MedallaListener {
    companion object {
        var medallaselect: String = ""
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun obtenerMedalla(medalla: String) {
        super.obtenerMedalla(medalla)
        //Log.d("medalla actual","medalla")
        medallaselect = medalla
    }
}
