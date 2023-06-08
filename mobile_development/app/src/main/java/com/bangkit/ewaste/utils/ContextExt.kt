package com.bangkit.ewaste.utils

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun View.showLoading(progressbarId : Int) {
    val progressBar = findViewById<ProgressBar>(progressbarId)
    progressBar?.visibility = View.VISIBLE
}

fun View.hideLoading(progressbarId: Int){
    val progressBar = findViewById<ProgressBar>(progressbarId)
    progressBar?.visibility = View.GONE
}