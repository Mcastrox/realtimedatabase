package com.example.tutoapp

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_profile.*
import java.util.*

class ProfileActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var tvname : TextView
    private lateinit var tvemail : TextView
    private lateinit var userTel:TextView
    private lateinit var modName:TextView
    private lateinit var modDireccion:TextView
    private lateinit var modTel:TextView
    private lateinit var uid:String
    private lateinit var mStorageRef : StorageReference
    var selected : Uri? =null
    private lateinit var imageUser: ImageView

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        initialise()
        modificar_info.setOnClickListener {
            guardar()
        }
        imageSelected.setOnClickListener {
            selectImage()
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

        mStorageRef = FirebaseStorage.getInstance().reference

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
    @RequiresApi(Build.VERSION_CODES.M)
    fun selectImage(){
        if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1)
        }
        else{
            val intent= Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent,2)
        }
        val uid=  UUID.randomUUID()
        val imageName = "images/$uid.jpg"
        val storageReference = mStorageRef!!.child(imageName)
        storageReference.putFile(selected!!)

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)
    {
        if(requestCode == 1){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                val intent = Intent (Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent,2)
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == 2 && resultCode == Activity.RESULT_OK && data != null ){
            selected= data.data
            imageUser.setImageURI(selected)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }




}
