package com.example.tarea

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.tarea.databinding.FragmentAnswerBinding
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_answer.*
import kotlinx.android.synthetic.main.fragment_second.*

/**
 * A simple [Fragment] subclass.
 */
class AnswerFragment : Fragment() {
    private lateinit var txt_answer:TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.title=getString(R.string.AnswerFragmentTitle)
        var binding = DataBindingUtil.inflate<FragmentAnswerBinding>(inflater,R.layout.fragment_answer,container,false)
        binding.txtAnswer.text = MainActivity.medallaselect
        return binding.root




    }



}
