package com.abdullahafzaldev.regalos.util

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.swiperefreshlayout.widget.CircularProgressDrawable

class Loading(context: Context) {
    var progressDialog: CircularProgressDrawable = CircularProgressDrawable(context)
    var img : ImageView=ImageView(context)

    init {
        progressDialog.strokeWidth = 5f
        progressDialog.setColorSchemeColors(Color.GRAY)
        progressDialog.centerRadius = 10f
        img.setImageDrawable(progressDialog)
//        progressDialog.setMessage("Loading...")
//        progressDialog.setCancelable(false)
    }


    fun showLoading(){
       progressDialog.start()
    }
    fun hideLoading(){
        if(progressDialog.isRunning){
           // progressDialog.stop()
        }
    }


}