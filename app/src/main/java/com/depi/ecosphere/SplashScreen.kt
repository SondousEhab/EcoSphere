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

        // الانتقال إلى MainActivity بعد 3 ثواني
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, Onboarding01::class.java)
            startActivity(intent)
            finish() // إغلاق شاشة البداية
        }, 3000) // 3000 ملي ثانية = 3 ثواني
    }
}
