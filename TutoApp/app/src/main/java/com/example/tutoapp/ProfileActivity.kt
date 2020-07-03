package com.example.tutoapp

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_profile.*
import java.util.*

class ProfileActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var tvemail: TextView
    private lateinit var modName: TextView
    private lateinit var modLastName: TextView
    private lateinit var modDireccion: TextView
    private lateinit var imageSelected: ImageView
    private lateinit var modTel: TextView
    private lateinit var uid: String
    private var selected: Uri? = null
    private lateinit var mStorageRef: StorageReference

    var toolbar : Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        initialise()
        modificar_info.setOnClickListener {

            if(modDireccion.text.isNotEmpty() && modLastName.text.isNotEmpty() && modName.text.isNotEmpty()
                    && modTel.text.isNotEmpty()) {
                guardar()
            }else{

                Toast.makeText(this, "Por favor llene todos los campos", Toast.LENGTH_LONG).show()

            }
        }

        toolbar = findViewById(R.id.toolbar)
        toolbar?.setTitle(R.string.modify_bar_txt)
        setSupportActionBar(toolbar)

        var actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        /*val user: FirebaseUser?=mAuth.currentUser
        mDataBaseReference= FirebaseDatabase.getInstance().getReference("Users")
        uid = user?.uid!!
        val mUser= mAuth!!.currentUser
        val mUserReference= mDataBaseReference!!.child(mUser!!.uid)*/

    }


    //update image
    private fun updateImage() {
        //para cambiar la imagen
        val uuid = UUID.randomUUID()

        if (selected != null) {
            var urlString = selected.toString()
            var ext = urlString.substring(urlString.lastIndexOf(".") + 1) //extrellendo la extension
            val imageName = "images/$uuid.${ext}"
            var storageReference = mStorageRef!!.child(imageName)
            storageReference.putFile(selected!!)
                .addOnSuccessListener {

                    // storing the media URL if upload success
                    //val downloadURL = taskSnapshot.metadata.toString()
                    val newReference = FirebaseStorage.getInstance().getReference(imageName)
                    newReference.downloadUrl
                        .addOnSuccessListener { uri ->
                            val downloadURL = uri.toString()
                            println(downloadURL)
                            //agregaremos el dato al usuario logueado
                            val user: FirebaseUser? = auth.currentUser
                            val ref = FirebaseDatabase.getInstance().getReference("Users")
                            val userRef = ref.child(user?.uid!!)

                            //con esto agremanos la ruta
                            userRef.child("urlImage").setValue(uri.toString())
                            //imageUser.setImageURI(uri.)
                        }
                }
                .addOnFailureListener { exception ->
                    if (exception != null) {
                        Toast.makeText(this, exception.localizedMessage, Toast.LENGTH_LONG).show()
                    }
                }
                .addOnCompleteListener { task ->
                    if (task.isComplete) {
                        Toast.makeText(this, "Post Added!", Toast.LENGTH_LONG).show()

                        // intent
                    }
                }

        }

        selected = null

        Toast.makeText(this, "Guardado con exito", Toast.LENGTH_LONG).show()

    }


    private fun guardar() {
        val referencia = FirebaseDatabase.getInstance().getReference("Users").child(uid)
        referencia.child("direccion").setValue(user_direccion.text.toString())
        referencia.child("telefono").setValue(user_telefono.text.toString())
        referencia.child("Name").setValue(user_name.text.toString())
        referencia.child("lastName").setValue(user_lastname.text.toString())

        updateImage()
    }

    private fun initialise() {
        auth = FirebaseAuth.getInstance()
        val user: FirebaseUser? = auth.currentUser
        //para los textos
        tvemail = findViewById(R.id.tvemail)
        modName = findViewById(R.id.user_name)
        modLastName = findViewById(R.id.user_lastname)
        modDireccion = findViewById(R.id.user_direccion)
        modTel = findViewById(R.id.user_telefono)
        imageSelected = findViewById(R.id.imageSelected)
        uid = user?.uid!!

        tvemail.text = user?.email!!.toString()

        //referencia para el storage
        mStorageRef = FirebaseStorage.getInstance().reference

        //buscando el nombre
        val ref = FirebaseDatabase.getInstance().getReference("Users")
        val userRef = ref.child(user?.uid!!)

        // tvname.text = user?.uid!!.toString()


        //para seleccionar la imagen
        imageSelected.setOnClickListener { selectImage() }


        userRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(dataSnapshot: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // sustituir nombre por "Name"
                modName.text = dataSnapshot.child("Name").value as String
                modLastName.text = dataSnapshot.child("lastName").value as String
                modDireccion.text = dataSnapshot.child("direccion").value as String
                modTel.text = dataSnapshot.child("telefono").value as String

                //agregando la imagen  imageSelected

                if (dataSnapshot.child("urlImage").exists()) {

                    val url = dataSnapshot.child("urlImage").value as String

                    Picasso.get().load(url).into(imageSelected);
                }


            }

        })

    }


    private fun selectImage() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1)
            }
        } else {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, 2)
        }


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val intent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, 2)
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {
            selected = data.data!!
            imageSelected.setImageURI(selected)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


}
