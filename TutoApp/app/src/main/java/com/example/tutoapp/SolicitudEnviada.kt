package com.example.tutoapp

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tutoapp.adapter.SolicitudEnviadaAdapter
import com.example.tutoapp.databinding.FragmentSolicitudEnviadaBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.example.tutoapp.viewmodel.TutorViewModel

class SolicitudEnviada : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var uidEstudiante: String
    private lateinit var adapter: SolicitudEnviadaAdapter

    private val viewModel by lazy { ViewModelProvider(this).get(TutorViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getCurrentUser()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                          savedInstanceState: Bundle?) : View?  {

        val binding = DataBindingUtil.inflate<FragmentSolicitudEnviadaBinding>(inflater,R.layout.fragment_solicitud_enviada,container,false)

        observerData(binding)
        return binding.root
    }

    private fun getCurrentUser() {
        auth = FirebaseAuth.getInstance()
        val user: FirebaseUser? = auth.currentUser
        uidEstudiante = user?.uid!!
    }

    private fun observerData(binding: FragmentSolicitudEnviadaBinding) {
        var lista_enviadas = binding.listaEnviadas

        viewModel.getEstudianteSolicitud(uidEstudiante).observe(this, Observer {
            adapter = SolicitudEnviadaAdapter(requireActivity(), it)
            lista_enviadas.adapter = adapter

            if (it.isNullOrEmpty()) {
                binding.empty.visibility = View.VISIBLE
                binding.listaEnviadas.visibility = View.GONE

            } else {
                binding.empty.visibility = View.GONE
                binding.listaEnviadas.visibility = View.VISIBLE

                lista_enviadas.setOnItemClickListener { parent, view, position, id ->
                    val intent = Intent(activity, VerSolicitudEnviadaActivity::class.java)
                    intent.putExtra("solicitud", it[position])
                    startActivity(intent)
                }

            }

        })
    }
}