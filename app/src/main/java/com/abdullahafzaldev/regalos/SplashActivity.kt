package com.abdullahafzaldev.regalos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abdullahafzaldev.regalos.util.Utils

class SplashActivity : AppCompatActivity() {
    lateinit var utils: Utils
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        utils = Utils(this)
        /* if(!utils.isAppRunForFirstTime()){
             startActivity(Intent(this, GetStartedActivity::class.java))
             finish()
         }else{
             startActivity(Intent(this, MainActivity::class.java))
             finish()
         }*/
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}