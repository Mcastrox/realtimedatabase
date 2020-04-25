package com.example.tarea

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       var binding= DataBindingUtil.inflate<FragmentSecondBinding>(inflater,R.layout.fragment_second,container,false)
        binding.btnAnswer.setOnClickListener { view:View->
            view.findNavController().navigate(R.id.action_secondFragment_to_answerFragment)
        }
        return binding.root
    }

}
