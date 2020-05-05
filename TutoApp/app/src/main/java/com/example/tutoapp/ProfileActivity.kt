package com.example.tutoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var tvname : TextView
    private lateinit var tvemail : TextView
    private lateinit var userTel:TextView
    private lateinit var modName:TextView
    private lateinit var modDireccion:TextView
    private lateinit var modTel:TextView
    private lateinit var uid:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        initialise()
        modificar_info.setOnClickListener {
            guardar()
        }
        /*val user: FirebaseUser?=mAuth.currentUser
        mDataBaseReference= FirebaseDatabase.getInstance().getReference("Users")
        uid = user?.uid!!
        val mUser= mAuth!!.currentUser
        val mUserReference= mDataBaseReference!!.child(mUser!!.uid)*/

    }


    private fun guardar()
    {
        val referencia = FirebaseDatabase.getInstance().getReference("Users").child(uid)
        referencia.child("direccion").setValue(user_direccion.text.toString())
        referencia.child("telefono").setValue(user_telefono.text.toString())
        referencia.child("Name").setValue(user_name.text.toString())


        Toast.makeText(this,"Guardado con exito", Toast.LENGTH_LONG).show()

    }
    private fun initialise()
    {
        auth = FirebaseAuth.getInstance()
        val user:FirebaseUser?=auth.currentUser
        //para los textos
        tvname = findViewById(R.id.tvusername)
        tvemail = findViewById(R.id.tvemail)
        userTel=findViewById(R.id.user_tel)
        modName=findViewById(R.id.user_name)
        modDireccion=findViewById(R.id.user_direccion)
        modTel=findViewById(R.id.user_telefono)
        uid = user?.uid!!

        tvemail.text = user?.email!!.toString()



        //buscando el nombre
        val ref = FirebaseDatabase.getInstance().getReference("Users")
        val userRef = ref.child(user?.uid!!)

        // tvname.text = user?.uid!!.toString()
        userRef.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(dataSnapshot: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // sustituir nombre por "Name"
                tvname.text = dataSnapshot.child("Name").value as String
                modName.text=dataSnapshot.child("Name").value as String
                userTel.text=dataSnapshot.child("telefono").value as String
               modDireccion.text=dataSnapshot.child("direccion").value as String
                modTel.text=dataSnapshot.child("telefono").value as String



            }

        })

    }



}
