package com.example.shoestore.utils

import android.view.View

infix fun View.onClick( onClick:()->Unit){
  this.setOnClickListener {
    onClick()

  }
}