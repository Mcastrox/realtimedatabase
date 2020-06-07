package com.example.tutoapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

// Esto nos ayuda a tratar el objeto completo
class Model(var id : String,
            var name:String,
            var lastname: String,
            var correo: String,
            var telefono: String,
            var nivel: String,
            var ocupacion: String,
            var location : String,
            var img : Int ,
            var ruta: String = "" ): Serializable{
}