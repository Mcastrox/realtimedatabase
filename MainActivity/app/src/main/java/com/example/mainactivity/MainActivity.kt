package com.example.mainactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ListAdapter
import android.widget.ListView
import com.google.firebase.database.*
import com.google.firebase.database.core.Repo
import kotlinx.android.synthetic.main.activity_create_file.*
import kotlinx.android.synthetic.main.activity_main.*
import java.nio.channels.AsynchronousByteChannel

class MainActivity : AppCompatActivity() {
    private lateinit var ref: DatabaseReference
    private lateinit var lista: MutableList<Reporte>
    lateinit var reporte: Reporte


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.title="Taller"
        lista= mutableListOf()


         ref = FirebaseDatabase.getInstance().getReference("/Reportes")

        ref.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {

                for (e in p0.children) {
                    var nombre_cliente: String= ""
                    var placa : String=""
                    var trabajos: String=""
                    var repuestos : String=""
                    var fecha: String=""
                    var hora : String =""

                    if (e.child("Nombre_cliente").value != null) {
                        nombre_cliente= e.child("Nombre_cliente").value as String

                    }
                    if(e.child("Placa del carro").value != null ){
                        placa=e.child("Placa del carro").value as String
                    }
                    if(e.child("Trabajo a realizar").value != null ){
                        trabajos=e.child("Trabajo a realizar").value as String
                    }
                    if(e.child("Repuestos").value != null ){
                        repuestos=e.child("Repuestos").value as String
                    }
                    if(e.child("Hora").value != null ){
                       hora=e.child("Hora").value as String
                    }
                    if(e.child("Fecha").value != null ){
                       fecha=e.child("Fecha").value as String
                    }


                     reporte=Reporte(nombre_cliente,placa,trabajos,repuestos,hora,fecha)
                    lista.add(reporte)
                    val adapter = ReporteAdapter(applicationContext,R.layout.item_reporte,lista)
                    mostrar.adapter=adapter



                }



        }
        })

        mostrar.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, VerReporte::class.java)
            intent.putExtra("nombre_cliente", reporte.nombre_cliente)
            intent.putExtra("placa", reporte.placa)
            intent.putExtra("trabajos", reporte.trabajos)
            intent.putExtra("repuestos", reporte.repuestos)
            intent.putExtra("fecha", reporte.fecha)
            intent.putExtra("hora", reporte.hora)
            startActivity(intent)
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater= menuInflater
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.addpost){
        val intent = Intent(applicationContext,CreateFile::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

}
