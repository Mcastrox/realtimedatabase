package com.example.tutoapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*
import java.util.*

class Register_Activity : AppCompatActivity() {
    private lateinit var txtName: EditText
    private lateinit var txtLastname: EditText
    private lateinit var txtEmail: EditText
    private lateinit var txtTelefono: EditText
    private lateinit var txtPassword: EditText
    private lateinit var txtDireccion:EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var dbReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        txtName=findViewById(R.id.txt_name)
        txtLastname=findViewById(R.id.txt_lastname)
        txtEmail=findViewById(R.id.txt_email)
        txtPassword=findViewById(R.id.txt_password)
        txtTelefono=findViewById(R.id.txt_telefono)
        txtDireccion=findViewById(R.id.txt_direccion)
        progressBar= this.findViewById(R.id.progressBar)
        database= FirebaseDatabase.getInstance()
        auth=FirebaseAuth.getInstance()
        dbReference=database.reference.child("Users")

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

    }
    fun register(view:View){
        if(cbx_politics.isChecked) {
            createNewUser()
        }
        else {
            Toast.makeText(this,"Por favor acepta los terminos de politica sale pete",Toast.LENGTH_LONG).show()
        }

    }
    private fun createNewUser(){
        val name: String = txt_name.text.toString()
        val lastName: String = txt_lastname.text.toString()
        val email: String = txt_email.text.toString()
        val pass: String = txt_password.text.toString()
        val tel: String =txt_telefono.text.toString()
        val direccion: String=txt_direccion.text.toString()

        if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(lastName)&&!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(pass)&&!TextUtils.isEmpty(tel)&&!TextUtils.isEmpty(direccion)){
            progressBar.visibility=View.VISIBLE
            auth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this){
                        task->
                    if(task.isComplete){
                        val user:FirebaseUser?=auth.currentUser
                        verifyEmail((user))

                        val userBD=dbReference.child(user?.uid!!)
                        userBD.child("Name").setValue(name)
                        userBD.child("correo").setValue(email)
                        userBD.child("lastName").setValue(lastName)
                        userBD.child("pass").setValue(pass)
                        userBD.child("telefono").setValue(tel)
                        userBD.child("direccion").setValue(direccion)
                        userBD.child("Rol").setValue("Estudiante")
                        userBD.child("urlImage").setValue("0")
                        userBD.child("ID").setValue(user.uid!!)
                        action()
                    }

                }
        }
        else{
            Toast.makeText(this,"Por favor rellene todos los campos",Toast.LENGTH_LONG).show()
        }
    }

    private fun action (){
        startActivity(Intent(this,MainActivity::class.java))

    }

    private fun verifyEmail(user:FirebaseUser?){
        user?.sendEmailVerification()
            ?.addOnCompleteListener(this){
                    task ->
                if(task.isComplete){
                    Toast.makeText(this,"Email enviado con exito",Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this,"error al enviar",Toast.LENGTH_LONG).show()
                }
            }
    }
}