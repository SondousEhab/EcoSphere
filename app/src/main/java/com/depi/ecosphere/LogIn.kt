package com.depi.ecosphere

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LogIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_log_in)

        // معالجة الحواف (Edge-to-Edge)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //  لما المستخدم يضغط على "Sign up now"
        val signupText = findViewById<TextView>(R.id.SignupNow)
        signupText.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
            finish() // عشان مايرجعش للـ LogIn لما يدوس Back
        }
    }
}
