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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.title="Taller"
        lista= mutableListOf()


         ref = FirebaseDatabase.getInstance().getReference("Reportes")

        ref.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {
                var nombre_cliente: String= ""
                for (e in p0.children) {

                    if (e.child("Nombre_cliente").value != null) {
                        nombre_cliente= e.child("Nombre_cliente").value as String

                    }
                    val reporte=Reporte(nombre_cliente,"abc123","motor","cambiar","12:30 ","22/7/2020")
                    lista.add(reporte)
                    val adapter = ReporteAdapter(applicationContext,R.layout.item_reporte,lista)
                    mostrar.adapter=adapter

                }

        }
        })



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
