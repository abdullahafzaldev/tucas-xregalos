package com.abdullahafzaldev.regalos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    companion object{
        var rateDialogShow = false
    }



    override fun onBackPressed() {

    }
}