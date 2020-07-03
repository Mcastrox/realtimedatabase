package com.example.tutoapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.tutoapp.models.Disciplina
import com.example.tutoapp.R

class GvAdapter(private val mcontext: Context, private val arrayList: ArrayList<Disciplina>) :
    ArrayAdapter<Disciplina>(mcontext, 0, arrayList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = LayoutInflater.from(mcontext).inflate(R.layout.item_gv, parent, false)
        var tv_disciplina: TextView = view.findViewById(R.id.tv_disciplinas)

        if (arrayList[position].seleccionado) {
            var disciplina: String? = arrayList[position].name
            tv_disciplina.text = disciplina.toString()

        }
        return view

    }

}