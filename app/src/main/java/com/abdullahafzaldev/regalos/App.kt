package com.abdullahafzaldev.regalos

import android.app.Application
import com.google.firebase.messaging.FirebaseMessaging

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseMessaging.getInstance().subscribeToTopic("new")
            .addOnCompleteListener { task ->
                var msg = "ok"
                if (!task.isSuccessful) {
                    msg = "fails"
                }
                //Log.d(TAG, msg)

            }
    }
}