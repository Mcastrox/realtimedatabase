package com.example.tutoapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.tutoapp.databinding.ActivityVerSolicitudBinding
import com.example.tutoapp.models.TutoriaModel
import com.example.tutoapp.viewmodel.TutorViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.select_contact_form.view.*


class VerSolicitudActivity : AppCompatActivity() {
    private lateinit var idEstudiante: String
    private lateinit var binding : ActivityVerSolicitudBinding
    private lateinit var idTutor : String
    private lateinit var idSolicitud: String
    private lateinit var correoTutor: String
    private lateinit var telefonoTutor: String
    private var estadoSolicitud : Array<String> = arrayOf("Aceptada","Rechazada")
    private val viewModel by lazy { ViewModelProvider(this).get(TutorViewModel::class.java) }

    var toolbar : Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_solicitud)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ver_solicitud)

        initialize()

        toolbar = findViewById(R.id.toolbar)
        toolbar?.setTitle(R.string.ver_solicitud_txt)
        setSupportActionBar(toolbar)

        var actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        
    }

    fun initialize() {
        val solicitud = intent.getSerializableExtra("solicitud") as TutoriaModel

        idEstudiante = solicitud.solicitante
        idTutor = solicitud.tutorSolicitado
        idSolicitud = solicitud.id
        correoTutor= solicitud.correoTutor
        telefonoTutor= solicitud.telefonoTutor
        binding.apply {
            nombreEstudiante.text = solicitud.nombre_estudiante + " " + solicitud.apellido_estudiante
            fechaTutoria.text = solicitud.fecha
            ubicacionSolicitud.text = solicitud.direccion
            horaTutoria.text = solicitud.hora
            notasTutoria.text = solicitud.nota
            tvMateria.text = solicitud.categoria
            aceptarTutoria.setOnClickListener {
                /*val mDialogView = LayoutInflater.from(this@VerSolicitudActivity).inflate(R.layout.select_contact_form, null)
                val mBuilder = AlertDialog.Builder(this@VerSolicitudActivity).setView(mDialogView).setTitle("Formulario de Contacto")
                val mAlertDialog = mBuilder.show()
                mDialogView.wha.setOnClickListener{
                    sendWhatsApp()
                    mAlertDialog.dismiss()
                }
                mDialogView.mail.setOnClickListener{
                    sendEmail(correoTutor,"TutoAppMail","Este es un mensaje de prueba para contactar a tu tutor")
                    mAlertDialog.dismiss()
                }*/
                sendWhatsApp(telefonoTutor)
                viewModel.updateEstadoSolicitud(idTutor,idEstudiante,idSolicitud,estadoSolicitud[0])
                Toast.makeText(this@VerSolicitudActivity, "Has aceptado la solicitud de ${solicitud.nombre_estudiante}", Toast.LENGTH_LONG).show()

                onBackPressed()
            }
            rechazarTutoria.setOnClickListener {
                viewModel.updateEstadoSolicitud(idTutor,idEstudiante,idSolicitud,estadoSolicitud[1])
                Toast.makeText(this@VerSolicitudActivity, "Has rechazado la solicitud de ${solicitud.nombre_estudiante}", Toast.LENGTH_LONG).show()
                onBackPressed()

            }
        }

        Picasso.get().load(solicitud.foto_estudiante).into(binding.fotoEstudiante)

    }

    private fun sendWhatsApp(numero: String) {
/* solo abrir wha
        val intent = packageManager.getLaunchIntentForPackage("com.whatsapp")*/
val intent = Intent(Intent.ACTION_VIEW)
        intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+"+503"+numero))
        startActivity(intent)
    }

    private fun sendEmail(correoTutor: String,asunto: String,message: String ) {
        val intent= Intent(Intent.ACTION_SEND)
        intent.data= Uri.parse("mailto:")
        intent.type= "text/plain"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(correoTutor))
        intent.putExtra(Intent.EXTRA_SUBJECT,asunto)
        intent.putExtra(Intent.EXTRA_TEXT,message)

        try{
        startActivity(Intent.createChooser(intent,"Elige una aplicacion para contactar"))
        }
        catch (e: Exception){
            Toast.makeText(this@VerSolicitudActivity,e.message,Toast.LENGTH_LONG).show()
        }

    }

}

