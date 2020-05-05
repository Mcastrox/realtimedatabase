package com.example.tutoapp

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.tutoapp.databinding.FragmentPerfilBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_central.*
import kotlinx.android.synthetic.main.activity_mperfil.*
import kotlinx.android.synthetic.main.fragment_perfil.view.*
import java.util.*
import java.util.jar.Manifest

/**
 * A simple [Fragment] subclass.
 */
class PerfilFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var username: TextView
    private lateinit var usermail: TextView
    private lateinit var userTel: TextView
    private lateinit var imageUser: ImageView
    private lateinit var mStorageRef : StorageReference
    var selected : Uri? =null

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        val binding = DataBindingUtil.inflate<FragmentPerfilBinding>(inflater,R.layout.fragment_perfil,container,false)

        bindingView(binding)
        initialize()

        binding.miPerfil.setOnClickListener {
            startActivity(Intent(activity,ProfileActivity::class.java))
        }
        binding.newTutor.setOnClickListener {
            startActivity(Intent(activity,TutorActivity::class.java))
        }

        binding.selectImage.setOnClickListener {
            selectImage()
        }

        return binding.root


    }

    fun bindingView(binding: FragmentPerfilBinding){
        username = binding.username
        usermail = binding.usermail
        userTel = binding.mperfilTelefono
        imageUser=binding.selectImage

    }
    fun initialize(){
        auth = FirebaseAuth.getInstance()
        val user:FirebaseUser?=auth.currentUser
        val ref = FirebaseDatabase.getInstance().getReference("Users")
        val userRef = ref.child(user?.uid!!)

         mStorageRef =FirebaseStorage.getInstance().reference

        usermail.text = user?.email!!.toString()
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
    fun selectImage(){
        if(ContextCompat.checkSelfPermission(activity!!,android.Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1)
        }
        else{
            val intent=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
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
                val intent = Intent (Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
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
