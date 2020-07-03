package com.example.tutoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ListView
import androidx.appcompat.widget.Toolbar
import com.example.tutoapp.adapter.TutorAdapter
import com.example.tutoapp.models.Disciplina
import com.example.tutoapp.models.Model
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class TutorFiltradoActivity : AppCompatActivity() {

    private var listaTutores = mutableListOf<Model>()
    private lateinit var adapter: TutorAdapter
    private lateinit var lista_categoriaSeleccionada: ListView


    var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutor_filtrado)

        var eleccion = intent.getStringExtra("seleccion")

        lista_categoriaSeleccionada = findViewById(R.id.lista_categoriaSeleccionada)
        adapter = TutorAdapter(this, listaTutores)

        val ref = FirebaseDatabase.getInstance().getReference("Users") // referencia a la bd
        ref.addValueEventListener(object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (listaTutores.size > 0) {
                    listaTutores.clear()
                }
                for (e in p0.children) {
                    var lastName: String = ""
                    var email: String = ""
                    var nivel: String = ""
                    var ocupation: String = ""
                    var cellphone: String = ""
                    var direccion: String = ""
                    var rol: String = ""
                    var name: String = ""
                    var ruta: String = ""
                    var descripcion: String = ""
                    var categoria: String = eleccion
                    var listaDisciplina: ArrayList<Disciplina>  = arrayListOf<Disciplina>()
                    var id: String = e.child("ID").value as String

                    if (e.child("Name").value != null) {
                        name = e.child("Name").value as String
                    }
                    if (e.child("lastName").value != null) {
                        lastName = e.child("lastName").value as String
                    }
                    if (e.child("direccion").value != null) {
                        direccion = e.child("direccion").value as String
                    }
                    if (e.child("correo").value != null) {
                        email = e.child("correo").value as String
                    }
                    if (e.child("telefono").value != null) {
                        cellphone = e.child("telefono").value as String
                    }
                    if (e.child("nivel").value != null) {
                        nivel = e.child("nivel").value as String
                    }
                    if (e.child("ocupacion").value != null) {
                        ocupation = e.child("ocupacion").value as String
                    }
                    if (e.child("Rol").value != null) {
                        rol = e.child("Rol").value as String
                    }
                    if (e.child("Descripcion").value != null) {
                        descripcion = e.child("Descripcion").value as String
                    }
                    if (e.child("urlImage").value != null) {
                        ruta = e.child("urlImage").value as String
                    }
                    if (e.child("disciplinas").exists()) {
                        for (item in 0..11) {
                            val name = e.child("disciplinas").child("$item").child("name").value as String
                            val isSelected = e.child("disciplinas").child("$item")
                                .child("seleccionado").value as Boolean
                            if(isSelected){
                                listaDisciplina.add(
                                    Disciplina(
                                        "$item",
                                        name,
                                        "",
                                        isSelected
                                    )
                                )
                            }
                        }
                    }


                    if (rol == "Tutor") {
                        if (e.child("disciplinas").child("${categoria}")
                                .child("seleccionado").value == true
                        ) {
                            listaTutores.add(
                                Model(
                                    id,
                                    name,
                                    lastName,
                                    email,
                                    cellphone,
                                    nivel,
                                    ocupation,
                                    direccion,
                                    R.drawable.ic_art,
                                    ruta,
                                    descripcion,
                                    listaDisciplina
                                )
                            )
                        }
                    }

                }


                lista_categoriaSeleccionada.adapter = adapter

                if (listaTutores.isNullOrEmpty()) {
                    findViewById<LinearLayout>(R.id.empty).visibility = View.VISIBLE
                    findViewById<ListView>(R.id.lista_categoriaSeleccionada).visibility = View.GONE

                } else {
                    findViewById<LinearLayout>(R.id.empty).visibility = View.GONE
                    findViewById<ListView>(R.id.lista_categoriaSeleccionada).visibility =
                        View.VISIBLE
                }

                lista_categoriaSeleccionada.setOnItemClickListener { parent, view, position, id ->
                    val intent =
                        Intent(this@TutorFiltradoActivity, PseleccionadoActivity::class.java)
                    intent.putExtra("tutor", listaTutores[position])
                    intent.putExtra("seleccion", eleccion)
                    startActivity(intent)
                }
            }
        })


        toolbar = findViewById(R.id.toolbar)
        toolbar?.setTitle(R.string.ToolBarTitle)
        setSupportActionBar(toolbar)

        var actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

    }

}

