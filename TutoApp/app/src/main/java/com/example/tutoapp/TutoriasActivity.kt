package com.example.tutoapp

import android.content.ClipData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.view.get
import androidx.databinding.DataBindingUtil

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tutoapp.databinding.ActivityTutoriasBinding
import com.example.tutoapp.viewmodel.TutorViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_tutorias.*
import kotlinx.android.synthetic.main.fragment_search.*

class TutoriasActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var uidTutor: String
    private lateinit var adapter: SolicitudAdapter
    private lateinit var binding: ActivityTutoriasBinding

    private val viewModel by lazy { ViewModelProvider(this).get(TutorViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorias)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tutorias)
        setSupportActionBar(binding.toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        getCurrentUser()
        observerData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.solicitud_menu, menu);
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       return when (item?.itemId) {
            R.id.next_page -> {
                startActivity(Intent(this,AtributesActivity::class.java))
                return true
            }

           android.R.id.home ->{
               onBackPressed()
               true
           }

            else -> (return super.onOptionsItemSelected(item))
        }
    }


    private fun getCurrentUser() {
        auth = FirebaseAuth.getInstance()
        val user: FirebaseUser? = auth.currentUser
        uidTutor = user?.uid!!
    }

    private fun observerData() {
        viewModel.getUserSolicitud(uidTutor).observe(this, Observer {
            adapter = SolicitudAdapter(this, it)
            lista_solicitudes.adapter = adapter

            if (it.isNullOrEmpty()){
                binding.empty.visibility = View.VISIBLE
                binding.listaSolicitudes.visibility = View.GONE

            }else{
                binding.empty.visibility = View.GONE
                binding.listaSolicitudes.visibility = View.VISIBLE

                lista_solicitudes.setOnItemClickListener { parent, view, position, id ->
                    val intent = Intent(this, VerSolicitudActivity::class.java)
                    intent.putExtra("solicitud", it[position])
                    startActivity(intent)
                }

            }

        })
    }

}
