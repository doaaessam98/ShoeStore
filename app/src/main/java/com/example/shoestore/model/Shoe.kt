package com.example.shoestore.model

import android.graphics.drawable.Drawable
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Shoe(
    var name: String,
    var size: String,
    var company: String,
    var description: String,
    var image : Drawable
)