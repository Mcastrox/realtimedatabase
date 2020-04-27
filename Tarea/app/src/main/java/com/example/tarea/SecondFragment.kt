package com.example.tarea

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.tarea.databinding.FragmentAnswerBinding
import com.example.tarea.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass.
 */
class SecondFragment : Fragment() {
    private lateinit var rdbtn1:RadioButton
    private var selected : String = ""
    var listener: MedallaListener ? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.title=getString(R.string.SecondFragmentTitle)

        var binding= DataBindingUtil.inflate<FragmentSecondBinding>(inflater,R.layout.fragment_second,container,false)
        binding.btnAnswer.setOnClickListener { view:View->

            if(binding.rdbt01.isChecked)
            {
                selected = "Una Medalla"
            }
            if(binding.rdbt02.isChecked)
            {
                selected = "dos Medallas"
            }
            if(binding.rdbt03.isChecked)
            {
                selected = "tres Medallas"
            }
            if(binding.rdbt04.isChecked)
            {
                selected = "Cuatro Medallas"
            }
            //aqui mando la opcion seleccionada
            listener?.obtenerMedalla(selected
            )
            view.findNavController().navigate(R.id.action_secondFragment_to_answerFragment)
        }
        return binding.root
        }

        interface  MedallaListener {
            fun  obtenerMedalla(medalla:String){}
        }
        override fun onAttach(context: Context) {
            super.onAttach(context)
            try {
                listener = context as MedallaListener
            }catch (e:ClassCastException){
                throw ClassCastException(context.toString() + " Debes de implementar la iterfaz")
            }

        }


    }
