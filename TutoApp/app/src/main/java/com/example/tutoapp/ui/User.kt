package com.example.tutoapp.ui

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User(val uid: String, val username: String, val ruta: String): Parcelable {
    constructor() : this("", "", "")
}