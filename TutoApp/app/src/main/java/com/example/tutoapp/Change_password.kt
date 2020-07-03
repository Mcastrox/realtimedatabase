package com.example.tutoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_register.*

class Change_password : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var etchange_pass: EditText
    private lateinit var etnew_pass: EditText
    private lateinit var etconfirm_pass: EditText

    var toolbar : Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        auth = FirebaseAuth.getInstance()

        etchange_pass = findViewById(R.id.pass_change)
        etnew_pass = findViewById(R.id.pass_new)
        etconfirm_pass = findViewById(R.id.confirm_pass)

        toolbar = findViewById(R.id.toolbar)
        toolbar?.setTitle(R.string.change_password)
        setSupportActionBar(toolbar)

        var actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        modificar_info.setOnClickListener{
            changePassword()
        }

    }


    private fun changePassword() {

        if (etnew_pass.text.length < 6 ){
            etnew_pass.setError("La contraseña debe tener al menos 6 caracteres.")
        }

        if (etchange_pass.text.isNotEmpty() && etnew_pass.text.isNotEmpty() && etconfirm_pass.text.isNotEmpty() && etnew_pass.text.length >= 6) {

            if (etnew_pass.text.toString().equals(etconfirm_pass.text.toString())) {

                val user = auth.currentUser
                if (user != null && user.email != null) {
                    val credential = EmailAuthProvider
                        .getCredential(user.email!!, etchange_pass.text.toString())

                    user?.reauthenticate(credential)
                        ?.addOnCompleteListener {
                            if (it.isSuccessful) {

                                user?.updatePassword(etnew_pass.text.toString())
                                    ?.addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            Toast.makeText(
                                                this,
                                                "Tu contraseña ha sido actualizada.",
                                                Toast.LENGTH_LONG
                                            ).show()

                                            etchange_pass.text.clear()
                                            etnew_pass.text.clear()
                                            etconfirm_pass.text.clear()
                                        }
                                    }
                            } else {
                                Toast.makeText(this, "Contraseña actual incorrecta.", Toast.LENGTH_LONG)
                                    .show()
                            }
                        }
                }
            } else {
                etconfirm_pass.setError("Las contraseñas no son iguales.")
                etconfirm_pass.text.clear()
            }
        }else {
            Toast.makeText(this, "Por favor llene todos los campos.", Toast.LENGTH_LONG).show()
        }
    }
}




