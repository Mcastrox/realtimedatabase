package com.example.tutoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_mperfil.*

class MperfilActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var username : TextView
    private lateinit var usermail : TextView
    private lateinit var userTel: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mperfil)
        initialise()

        new_tutor.setOnClickListener {
            startActivity(Intent(this,TutorActivity::class.java))
        }
        miPerfil.setOnClickListener {
            startActivity(Intent(this,ProfileActivity::class.java))
        }

    }
    private fun initialise()
    {
        auth = FirebaseAuth.getInstance()
        val user:FirebaseUser?=auth.currentUser
        //para los textos
        username = findViewById(R.id.username)
        usermail = findViewById(R.id.usermail)
        userTel=findViewById(R.id.mperfil_telefono)


        usermail.text = user?.email!!.toString()


        //buscando el nombre
        val ref = FirebaseDatabase.getInstance().getReference("Users")
        val userRef = ref.child(user?.uid!!)

        // tvname.text = user?.uid!!.toString()
        userRef.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(dataSnapshot: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // sustituir nombre por "Name"
                username.text = dataSnapshot.child("Name").value as String
                userTel.text=dataSnapshot.child("telefono").value as String


            }

        })

    }
}
