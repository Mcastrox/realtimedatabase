package com.example.tutoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tutoapp.ui.SolicitudActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.BlurTransformation
import kotlinx.android.synthetic.main.activity_pseleccionado.*

class PseleccionadoActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var nombre_estudiante: String
    private lateinit var apellido_estudiante: String

    private lateinit var url: String
    var mStorageRef: StorageReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pseleccionado)
        auth = FirebaseAuth.getInstance()
        val user: FirebaseUser? = auth.currentUser

        val tutor = intent.getSerializableExtra("tutor") as Model
        nombre_tutor.text = tutor.name
        lastname_tutor.text = tutor.lastname
        location_tutor.text = tutor.location
        ocupation_tutor.text = tutor.ocupacion
        nivel_tutor.text = tutor.nivel
        correo_tutor.text = tutor.correo
        telefono_tutor.text = tutor.telefono
        Picasso.get().load(tutor.ruta).into(image_tutor)
        Picasso.get().load(tutor.ruta).into(user_tutor)

        Picasso.get()
            .load(tutor.ruta)
            .transform(BlurTransformation(this, 25, 3))
            .into(user_tutor)

        mStorageRef = FirebaseStorage.getInstance().reference
        val ref = FirebaseDatabase.getInstance().getReference("Users")
        val userRef = ref.child(user?.uid!!)

        userRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(dataSnapshot: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                nombre_estudiante = dataSnapshot.child("Name").value as String
                apellido_estudiante = dataSnapshot.child("lastName").value as String
                url = dataSnapshot.child("urlImage").value as String

            }

        })


        action_contactar.setOnClickListener {
            val seleccion = intent?.getStringExtra("seleccion")
            val intent = Intent(this, SolicitudActivity::class.java)
            intent.putExtra("idEstudiante", user?.uid)
            intent.putExtra("nombre_estudiante", nombre_estudiante)
            intent.putExtra("apellido_estudiante", apellido_estudiante)
            intent.putExtra("foto_estudiante", url)
            intent.putExtra("idTutor", tutor.id)
            intent.putExtra("seleccion", seleccion)
            //startActivity(Intent(this,SolicitudActivity::class.java))
            startActivity(intent)
        }
    }
}
