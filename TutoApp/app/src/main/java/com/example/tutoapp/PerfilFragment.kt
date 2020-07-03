package com.example.tutoapp


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
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
import com.squareup.picasso.Picasso

/**
 * A simple [Fragment] subclass.
 */
class PerfilFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var username: TextView
    private lateinit var usermail: TextView
    private lateinit var userTel: TextView
    private lateinit var imageUser: ImageView
    private lateinit var logOut : Button
    private lateinit var rol : String
    var mStorageRef : StorageReference? =null

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
        binding.misTutorias.setOnClickListener {

            if(rol== "Estudiante"){
                startActivity(Intent(activity,TutorActivity::class.java))
            }
            if (rol== "Tutor") {
                startActivity(Intent(activity,TutoriasActivity::class.java))
            }
        }
        binding.changePassword.setOnClickListener {
            startActivity(Intent(activity,Change_password::class.java))
        }
        binding.logOut.setOnClickListener {

            Toast.makeText(activity!!,"Loggin Out... ", Toast.LENGTH_SHORT).show()
            logOut()
            if(auth.currentUser==null){

                val intent = Intent (activity!!, MainActivity::class.java)
                intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
            else{
                Toast.makeText(activity!!,"No funciono ", Toast.LENGTH_LONG).show()
            }
        }
        return binding.root


    }

    private fun bindingView(binding: FragmentPerfilBinding){
        username = binding.username
        usermail = binding.usermail
        userTel = binding.mperfilTelefono
        imageUser= binding.selectImage
        rol = ""
        logOut = binding.logOut


    }
    private fun logOut(){
        auth.signOut()
    }
    private fun initialize(){
        auth = FirebaseAuth.getInstance()
        val user:FirebaseUser?=auth.currentUser
        val ref = FirebaseDatabase.getInstance().getReference("Users")
        val userRef = ref.child(user?.uid!!)


        mStorageRef = FirebaseStorage.getInstance().reference

        usermail.text = user?.email!!.toString()
        userRef.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(dataSnapshot: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // sustituir nombre por "Name"
                username.text = dataSnapshot.child("Name").value as String
                userTel.text = dataSnapshot.child("telefono").value as String

                rol = dataSnapshot.child("Rol").value as String

                if (dataSnapshot.child("urlImage").exists()) {

                        val url = dataSnapshot.child("urlImage").value as String

                        Picasso.get().load(url).into(imageUser);

                }
            }

        })

    }


}
