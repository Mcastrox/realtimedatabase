package com.example.tutoapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row.view.*

class TutorAdapter(private val mContext: Context , private val listaTutores : List<Model>): ArrayAdapter<Model>(mContext,0,listaTutores) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.row,parent, false)
        val tutor = listaTutores[position]

        layout.textView1.text=tutor.name
        layout.textView2.text=tutor.lastname
        layout.ubicacion_solicitud.text = tutor.location
        layout.textView3.text=tutor.ocupacion

        layout.image.setImageResource(tutor.img)

        if(tutor.ruta == ""){
            layout.image.setImageResource(tutor.img)
        }
        else{
            Picasso.get().load(tutor.ruta).into(layout.image)
        }

        return layout
    }
}