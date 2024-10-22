package com.example.moviehive

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment


fun Fragment.toastMessage(message:String){
    Toast.makeText(requireContext(),message,Toast.LENGTH_LONG).show()
}
fun View.gone(){
    visibility = View.GONE
}
fun View.visible(){
    visibility = View.VISIBLE
}