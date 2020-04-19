package com.example.tutoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    lateinit var EditTextPass:EditText
    lateinit var EditTextEmail:EditText
    lateinit var ref : DatabaseReference
    lateinit var listView : ListView
    lateinit var heroList: MutableList<Hero>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         ref = FirebaseDatabase.getInstance().getReference("heroes")
        auth = FirebaseAuth.getInstance()
        EditTextEmail = findViewById(R.id.email_register)
        EditTextPass = findViewById(R.id.password_register)
        listView = findViewById(R.id.listView)



        heroList= mutableListOf()
        action_register.setOnClickListener {

            saveHero()
            ref.addValueEventListener(object :ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onDataChange(p0: DataSnapshot) {
                    if(p0!!.exists()){
                        for(h in p0.children){
                            val hero= h.getValue(Hero::class.java)
                            heroList.add(hero!!)
                        }
                        val adapter:=HeroAdapter(applicationContext,R.layout.activity_home,heroList)
                        listView.adapter=adapter
                    }
                }

            })
        }
    }
    private fun saveHero() {
        val email = EditTextEmail.text.toString().trim()
        val password = EditTextPass.text.toString().trim()
        if (email.isEmpty()) {
            EditTextEmail.error = "PLease enter an email"
        }


        val heroId = ref.push().key

        val hero = Hero(heroId!!, email, password)
        ref.child(heroId).setValue(hero).addOnCompleteListener {
        Toast.makeText(applicationContext, "hero saved successfully", Toast.LENGTH_LONG).show()
        }
    }
    public override fun onStart(){
        super.onStart()
        val currentUser: FirebaseUser? =auth.currentUser
        updateUI(currentUser)
    }

     fun updateUI(currentUser: FirebaseUser?) {

    }


}
