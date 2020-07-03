package com.example.tutoapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tutoapp.R
import com.example.tutoapp.models.Model
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rvrow.view.*

class ListAdapter(private val context: Context) : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private var tutorList = mutableListOf<Model>()

    fun setDataList(data : MutableList<Model>){
        tutorList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rvrow, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if(tutorList.size > 0){
            tutorList.size
        }else{
            0
        }
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val tutor = tutorList[position]
        holder.bindView(tutor)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindView(tutor : Model){
            Picasso.get().load(tutor.ruta).into(itemView.image)
            itemView.textView1.text=tutor.name
            itemView.textView2.text=tutor.lastname
            itemView.ubicacion_solicitud.text = tutor.location
            itemView.textView3.text=tutor.ocupacion
        }
    }


}