package com.depi.ecosphere

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class Onboarding02 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_onboarding02)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Skip → MainActivity
        findViewById<View>(R.id.skipText)?.setOnClickListener {
            startActivity(Intent(this, SignUp::class.java))
            finish()
        }
        // 🔹 ربط زر "Next" بالـ Onboarding03
        val nextButton = findViewById<MaterialButton>(R.id.nextButton)
        nextButton.setOnClickListener {
            val intent = Intent(this, Onboarding03::class.java)
            startActivity(intent)
        }
    }
}
