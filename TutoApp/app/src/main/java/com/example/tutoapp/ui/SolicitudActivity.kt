package com.example.tutoapp.ui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.example.tutoapp.R
import com.example.tutoapp.models.TutoriaModel
import com.example.tutoapp.viewmodel.TutorViewModel
import java.text.SimpleDateFormat
import java.util.*

class SolicitudActivity : AppCompatActivity() {
    private lateinit var txt_direccion: EditText
    private lateinit var txt_categoria: EditText
    private lateinit var txt_fecha: EditText
    private lateinit var txt_hora: EditText
    private lateinit var txt_notas: EditText
    private lateinit var btn_solicitar: Button
    private lateinit var idTutor: String
    private lateinit var estado: String
    private lateinit var nombre_estudiante: String
    private lateinit var apellido_estudiante: String
    private lateinit var nombre_tutor: String
    private lateinit var apellido_tutor: String
    private lateinit var foto_estudiante: String
    private lateinit var foto_tutor: String
    private lateinit var idEstudiante: String
    private lateinit var seleccion: String
    private lateinit var correoTutor: String
    private lateinit var telefonoTutor: String

    //lazy se usa para instanciar el objeto hasta que se necesite
    private val viewModel by lazy { ViewModelProvider(this).get(TutorViewModel::class.java) }

    var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solicitud)

        toolbar = findViewById(R.id.toolbar)
        toolbar?.setTitle(R.string.solicitud_txt)
        setSupportActionBar(toolbar)

        var actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        initialize()


        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        txt_fecha.setOnClickListener {
            val dp = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    txt_fecha.setText("$dayOfMonth/${month + 1}/$year")
                },
                year,
                month,
                day
            )

            dp.show()
        }

        txt_hora.setOnClickListener {
            val tp = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                c.set(Calendar.HOUR_OF_DAY, hourOfDay)
                c.set(Calendar.MINUTE, minute)
                txt_hora.setText(SimpleDateFormat("hh:mm a").format(c.time))
            }
            TimePickerDialog(
                this,
                tp,
                c.get(Calendar.HOUR_OF_DAY),
                c.get(Calendar.MINUTE),
                false
            ).show()
        }


        btn_solicitar.setOnClickListener {

            if(txt_direccion.text.isNotEmpty() && txt_categoria.text.isNotEmpty() && txt_fecha.text.isNotEmpty() &&
                    txt_hora.text.isNotEmpty() && txt_notas.text.isNotEmpty()) {

                crearSolicitud()
                finish()

            }else{

                Toast.makeText(this, "Por favor rellene todos los campos", Toast.LENGTH_LONG).show()

            }
        }



    }

    fun initialize() {
        idEstudiante = intent.getStringExtra("idEstudiante")
        nombre_estudiante = intent.getStringExtra("nombre_estudiante")
        foto_estudiante = intent.getStringExtra("foto_estudiante")
        idTutor = intent.getStringExtra("idTutor")
        foto_tutor = intent.getStringExtra("foto_tutor")
        apellido_estudiante = intent.getStringExtra("apellido_estudiante")
        nombre_tutor = intent.getStringExtra("nombre_tutor")
        apellido_tutor = intent.getStringExtra("apellido_tutor")
        txt_direccion = findViewById(R.id.txt_direccion)
        txt_categoria = findViewById(R.id.txt_categoria)
        txt_fecha = findViewById(R.id.txt_fecha)
        txt_hora = findViewById(R.id.txt_hora)
        txt_notas = findViewById(R.id.notas_tutor)
        estado = "En espera"
        btn_solicitar = findViewById(R.id.action_solicitar)
        correoTutor=intent.getStringExtra("correo_tutor")
        telefonoTutor=intent.getStringExtra("telefono_tutor")
        selectedCategory()

        txt_categoria.setText(seleccion)

    }

    private fun crearSolicitud() {
        var id = UUID.randomUUID().toString()

        var solicitud = TutoriaModel(
            id,
            txt_direccion.text.toString(),
            txt_categoria.text.toString(),
            txt_fecha.text.toString(),
            txt_hora.text.toString(),
            txt_notas.text.toString(),
            idEstudiante,
            idTutor,
            estado,
            nombre_estudiante,
            foto_estudiante,
            apellido_estudiante,
            nombre_tutor,
            apellido_tutor,
            foto_tutor,
            correoTutor,
            telefonoTutor
        )

        viewModel.postUserData(solicitud, idTutor, idEstudiante)

        Toast.makeText(this, "Solicitud enviada con exito", Toast.LENGTH_LONG).show()
    }


    private fun selectedCategory() {
        when (intent.getStringExtra("seleccion")) {
            "0" -> {
                seleccion = resources.getString(R.string.art_txt)
            }
            "1" -> {
                seleccion = resources.getString(R.string.languages_txt)
            }
            "2" -> {
                seleccion = resources.getString(R.string.math_txt)
            }
            "3" -> {
                seleccion = resources.getString(R.string.design_txt)
            }
            "4" -> {
                seleccion = resources.getString(R.string.economy_txt)
            }
            "5" -> {
                seleccion = resources.getString(R.string.social_abilities_txt)
            }
            "6" -> {
                seleccion = resources.getString(R.string.physics_txt)
            }
            "7" -> {
                seleccion = resources.getString(R.string.computer_science)
            }
            "8" -> {
                seleccion = resources.getString(R.string.chemistry_txt)
            }
            "9" -> {
                seleccion = resources.getString(R.string.music_txt)
            }
            "10" -> {
                seleccion = resources.getString(R.string.maths_s_txt)
            }
            "11" -> {
                seleccion = resources.getString(R.string.socials_sciences_txt)
            }
            else -> {
                seleccion = ""
            }
        }

    }

}
