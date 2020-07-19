package com.example.mainactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global.getString
import android.provider.Settings.Secure.getString
import android.widget.TextView
import androidx.core.content.res.TypedArrayUtils.getString

class VerReporte : AppCompatActivity() {
    private lateinit var nombre_cliente: TextView
    private lateinit var placa: TextView
    private lateinit var trabajos: TextView
    private lateinit var repuestos: TextView
    private lateinit var fecha: TextView
    private lateinit var hora: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_reporte)
        initialize()


    }
    fun initialize(){
        nombre_cliente=findViewById(R.id.clienteName)
        placa=findViewById(R.id.carId)
        trabajos=findViewById(R.id.trabajos)
        repuestos=findViewById(R.id.repuestos)
        fecha=findViewById(R.id.fecha)
        hora=findViewById(R.id.hora)

        nombre_cliente.text=intent.getStringExtra("nombre_cliente")
        placa.text=intent.getStringExtra("placa")
        trabajos.text=intent.getStringExtra("trabajos")
        repuestos.text=intent.getStringExtra("repuestos")
        fecha.text=intent.getStringExtra("fecha")
        hora.text=intent.getStringExtra("hora")
    }

}