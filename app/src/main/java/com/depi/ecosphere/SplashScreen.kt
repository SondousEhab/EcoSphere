package com.depi.ecosphere

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.os.Handler
import android.os.Looper

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val pref = UserPref(this)

        Handler(Looper.getMainLooper()).postDelayed({

            val nextIntent = if (pref.isLoggedIn()) {
                // لو اليوزر مسجل دخول قبل كده → دخّليه على MainActivity
                Intent(this, MainActivity::class.java)
            } else {
                // أول مرة أو مش لوج إن → روّحي Onboarding01
                Intent(this, Onboarding01::class.java)
            }

            startActivity(nextIntent)
            finish()
        }, 3000) // 3 ثواني
    }
}
