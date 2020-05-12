package com.example.tutoapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.tutorias.view.*

class SolicitudAdapter (private val mContext: Context, private val listaTutorias : List<TutoriaModel>): ArrayAdapter<TutoriaModel>(mContext,0,listaTutorias) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.tutorias, parent, false)
        val tutoria = listaTutorias[position]

        layout.categoria_tutoria.text= tutoria.categoria
        layout.fecha_tutoria.text= tutoria.fecha
        layout.hora_tutoria.text= tutoria.hora




        return layout
    }
}