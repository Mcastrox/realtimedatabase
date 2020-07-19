package com.example.mainactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import java.util.Random
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_create_file.*


class CreateFile : AppCompatActivity() {
    private  lateinit var clientName:String
    private  lateinit var carId:String
    private  lateinit var jobToDo:String
    private  lateinit var parts:String
    private  lateinit var time:String
    private  lateinit var date:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_file)

        guardar.setOnClickListener {
            createReport()
        }

    }
    private fun createReport(){
        var aleatrorio = Random()
        var id= aleatrorio.nextInt(100000)+1
        clientName = nombre_cliente.text.toString().trim()
        carId= placa_carro.text.toString().trim()
         jobToDo= arreglos.text.toString().trim()
         parts = repuestos.text.toString().trim()
        time = hora.text.toString().trim()
        date=fecha.text.toString().trim()


        val ref = FirebaseDatabase.getInstance().getReference("/Reportes/${id}")

        ref.child("Nombre_cliente").setValue(clientName)
        ref.child("Placa del carro").setValue(carId)
        ref.child("Trabajo a realizar").setValue(jobToDo)
        ref.child("Repuestos").setValue(parts)
        ref.child("Hora").setValue(time)
        ref.child("Fecha").setValue(date)
        action()

    }
    private fun action(){
       val intent= Intent(applicationContext,MainActivity::class.java)
        startActivity(intent)
    }
}