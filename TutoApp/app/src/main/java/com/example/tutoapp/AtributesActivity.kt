package com.example.tutoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_atributes.*

class AtributesActivity : AppCompatActivity() {

    lateinit var txtEducacion: EditText
    lateinit var txtOcupacion: EditText
    lateinit var txtDescripcion: EditText
    lateinit var listaDisciplina: ArrayList<Disciplina>
    lateinit var btnGuardar: Button

    //check box
    lateinit var cbdis1: CheckBox
    lateinit var cbdis2: CheckBox
    lateinit var cbdis3: CheckBox
    lateinit var cbdis4: CheckBox
    lateinit var cbdis5: CheckBox
    lateinit var cbdis6: CheckBox
    lateinit var cbdis7: CheckBox
    lateinit var cbdis8: CheckBox
    lateinit var cbdis9: CheckBox
    lateinit var cbdis10: CheckBox


    lateinit var uid: String
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atributes)

        initialize()

        initArrayDisciplina()
        cbdis1.setOnCheckedChangeListener { _, _ ->
            when {
                cbdis1.isChecked -> {
                    this.listaDisciplina[0].seleccionado = true
                }
                else -> {
                    this.listaDisciplina[0].seleccionado = false
                }
            }
        }

        cbdis2.setOnCheckedChangeListener { _, _ ->
            when {
                cbdis2.isChecked -> {
                    this.listaDisciplina[1].seleccionado = true
                }
                else -> {
                    this.listaDisciplina[1].seleccionado = false
                }
            }
        }

        cbdis3.setOnCheckedChangeListener { _, _ ->
            when {
                cbdis3.isChecked -> {
                    this.listaDisciplina[2].seleccionado = true
                }
                else -> {
                    this.listaDisciplina[2].seleccionado = false
                }
            }
        }

        cbdis4.setOnCheckedChangeListener { _, _ ->
            when {
                cbdis4.isChecked -> {
                    this.listaDisciplina[3].seleccionado = true
                }
                else -> {
                    this.listaDisciplina[3].seleccionado = false
                }
            }
        }
        cbdis5.setOnCheckedChangeListener { _, _ ->
            when {
                cbdis5.isChecked -> {
                    this.listaDisciplina[4].seleccionado = true
                }
                else -> {
                    this.listaDisciplina[4].seleccionado = false
                }
            }
        }
        cbdis6.setOnCheckedChangeListener { _, _ ->
            when {
                cbdis6.isChecked -> {
                    this.listaDisciplina[5].seleccionado = true
                }
                else -> {
                    this.listaDisciplina[5].seleccionado = false
                }
            }
        }
        cbdis7.setOnCheckedChangeListener { _, _ ->
            when {
                cbdis7.isChecked -> {
                    this.listaDisciplina[6].seleccionado = true
                }
                else -> {
                    this.listaDisciplina[6].seleccionado = false
                }
            }
        }
        cbdis8.setOnCheckedChangeListener { _, _ ->
            when {
                cbdis8.isChecked -> {
                    this.listaDisciplina[7].seleccionado = true
                }
                else -> {
                    this.listaDisciplina[7].seleccionado = false
                }
            }
        }
        cbdis9.setOnCheckedChangeListener { _, _ ->
            when {
                cbdis9.isChecked -> {
                    this.listaDisciplina[8].seleccionado = true
                }
                else -> {
                    this.listaDisciplina[8].seleccionado = false
                }
            }
        }
        cbdis10.setOnCheckedChangeListener { _, _ ->
            when {
                cbdis10.isChecked -> {
                    this.listaDisciplina[9].seleccionado = true
                }
                else -> {
                    this.listaDisciplina[9].seleccionado = false
                }
            }
        }

        guardar_tutor.setOnClickListener {

            guardar()
        }
    }


    private fun initArrayDisciplina() {
        this.listaDisciplina = ArrayList();
        this.listaDisciplina.add(Disciplina("1", "Arte", "", false))
        this.listaDisciplina.add(Disciplina("2", "Idiomas", "", false))
        this.listaDisciplina.add(Disciplina("3", "Matematicas", "", false))
        this.listaDisciplina.add(Disciplina("4", "Cocina", "", false))
        this.listaDisciplina.add(Disciplina("5", "Economia", "", false))
        this.listaDisciplina.add(Disciplina("6", "Habilidades Sociales", "", false))
        this.listaDisciplina.add(Disciplina("7", "Fisica", "", false))
        this.listaDisciplina.add(Disciplina("8", "Computacion", "", false))
        this.listaDisciplina.add(Disciplina("9", "Quimica", "", false))
        this.listaDisciplina.add(Disciplina("10", "Deportes", "", false))
    }

    private fun initialize() {
        this.txtEducacion = findViewById(R.id.educacion_tutor)
        this.txtOcupacion = findViewById(R.id.ocupacion_tutor)
        this.txtDescripcion = findViewById(R.id.descripcion_tutor)
        this.cbdis1 = findViewById(R.id.categoria_arte)
        this.cbdis2 = findViewById(R.id.categoria_idiomas)
        this.cbdis3 = findViewById(R.id.categoria_matematicas)
        this.cbdis4 = findViewById(R.id.categoria_cocina)
        this.cbdis5 = findViewById(R.id.categoria_economia)
        this.cbdis6 = findViewById(R.id.categoria_habilidadesSociales)
        this.cbdis7 = findViewById(R.id.categoria_fisica)
        this.cbdis8 = findViewById(R.id.categoria_computacion)
        this.cbdis9 = findViewById(R.id.categoria_quimica)
        this.cbdis10 = findViewById(R.id.categoria_deportes)

        this.btnGuardar = findViewById(R.id.guardar_tutor)

        auth = FirebaseAuth.getInstance()
        val user: FirebaseUser? = auth.currentUser
        uid = user?.uid!!

        Log.d("Uid-Usuario", uid.toString())
        //this.txtEducacion.text = uid.toString()
    }


    private fun guardar() {
        val referencia = FirebaseDatabase.getInstance().getReference("Users").child(uid)
        referencia.child("nivel").setValue(txtEducacion.text.toString())
        referencia.child("ocupacion").setValue(txtOcupacion.text.toString())
        referencia.child("Descripcion").setValue(txtDescripcion.text.toString())
        referencia.child("disciplinas").setValue(listaDisciplina)
        Toast.makeText(this, "Guardado con exito", Toast.LENGTH_LONG).show()




    }
}
