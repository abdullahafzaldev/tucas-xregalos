package com.abdullahafzaldev.regalos


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.abdullahafzaldev.regalos.util.Utils
import com.abdullahafzaldev.regalos.R
import kotlinx.android.synthetic.main.activity_get_started.*


class GetStartedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_started)
        continue_btn.setOnClickListener {
            Utils(this).setAppRunForFirstTime(true)
            startActivity(Intent(this, MainActivity::class.java))
            finishAffinity()
        }
    }
}