package com.example.mainactivity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_create_file.view.*
import kotlinx.android.synthetic.main.item_reporte.view.*

class ReporteAdapter( val mContext: Context, val layoutResId: Int, val lista_reportes: List<Reporte>): ArrayAdapter<Reporte>(mContext,0,lista_reportes) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
       val layoutInflater: LayoutInflater= LayoutInflater.from(mContext)
        val view : View = layoutInflater.inflate(layoutResId,null)
        val txtName= view.findViewById<TextView>(R.id.reporte)
        val reporte = lista_reportes[position]
        txtName.text=reporte.nombre_cliente
        return view

    }
}