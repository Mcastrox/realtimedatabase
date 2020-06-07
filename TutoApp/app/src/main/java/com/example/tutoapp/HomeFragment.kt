package com.example.tutoapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.databinding.DataBindingUtil
import com.example.tutoapp.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
private lateinit var seleccionada : String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater, R.layout.fragment_home, container, false)

        binding.arte.setOnClickListener {
            seleccionada="0"
            val intent = Intent(activity,TutorFiltradoActivity::class.java)
            intent.putExtra("seleccion", seleccionada)
            startActivity(intent)
        }
        binding.idiomas.setOnClickListener {
            seleccionada="1"
            val intent = Intent(activity,TutorFiltradoActivity::class.java)
            intent.putExtra("seleccion", seleccionada)
            startActivity(intent)
        }
        binding.matematicas.setOnClickListener {
            seleccionada="2"
            val intent = Intent(activity,TutorFiltradoActivity::class.java)
            intent.putExtra("seleccion", seleccionada)
            startActivity(intent)
        }
        binding.diseno.setOnClickListener {
            seleccionada="3"
            val intent = Intent(activity,TutorFiltradoActivity::class.java)
            intent.putExtra("seleccion", seleccionada)
            startActivity(intent)
        }
        binding.economia.setOnClickListener {
            seleccionada="4"
            val intent = Intent(activity,TutorFiltradoActivity::class.java)
            intent.putExtra("seleccion", seleccionada)
            startActivity(intent)
        }
        binding.habilidadesSociales.setOnClickListener {
            seleccionada="5"
            val intent = Intent(activity,TutorFiltradoActivity::class.java)
            intent.putExtra("seleccion", seleccionada)
            startActivity(intent)
        }
        binding.fisica.setOnClickListener {
            seleccionada="6"
            val intent = Intent(activity,TutorFiltradoActivity::class.java)
            intent.putExtra("seleccion", seleccionada)
            startActivity(intent)
        }
        binding.computacion.setOnClickListener {
            seleccionada="7"
            val intent = Intent(activity,TutorFiltradoActivity::class.java)
            intent.putExtra("seleccion", seleccionada)
            startActivity(intent)
        }
        binding.quimica.setOnClickListener {
            seleccionada="8"
            val intent = Intent(activity,TutorFiltradoActivity::class.java)
            intent.putExtra("seleccion", seleccionada)
            startActivity(intent)
        }
        binding.musica.setOnClickListener {
            seleccionada="9"
            val intent = Intent(activity,TutorFiltradoActivity::class.java)
            intent.putExtra("seleccion", seleccionada)
            startActivity(intent)
        }

        binding.mateSuperior.setOnClickListener {
            seleccionada="10"
            val intent = Intent(activity,TutorFiltradoActivity::class.java)
            intent.putExtra("seleccion", seleccionada)
            startActivity(intent)
        }
        binding.cienciasSociales.setOnClickListener {
            seleccionada="11"
            val intent = Intent(activity,TutorFiltradoActivity::class.java)
            intent.putExtra("seleccion", seleccionada)
            startActivity(intent)
        }


        return binding.root

    }

}
