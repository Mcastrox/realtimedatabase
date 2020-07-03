package com.example.tutoapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.tutoapp.R
import com.example.tutoapp.models.TutoriaModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.tutorias.view.*

class SolicitudAdapter(
    private val mContext: Context,
    private val listaTutorias: List<TutoriaModel>
) : ArrayAdapter<TutoriaModel>(mContext, 0, listaTutorias) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.tutorias, parent, false)
        val tutoria = listaTutorias[position]

        layout.nombre_estudiante.text = tutoria.nombre_estudiante
        layout.apellido_estudiante.text = tutoria.apellido_estudiante
        layout.ubicacion.text = tutoria.direccion
        layout.estado.text = tutoria.estado


        Picasso.get().load(tutoria.foto_estudiante).into(layout.foto_estudiante)
        
        return layout
    }
}