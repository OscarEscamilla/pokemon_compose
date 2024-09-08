package com.racso.pokemoncompose.utils

import android.content.Context
import android.view.View
import android.widget.Toast


fun Context.toast(txt: String, duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(this, txt, duration).show()
}