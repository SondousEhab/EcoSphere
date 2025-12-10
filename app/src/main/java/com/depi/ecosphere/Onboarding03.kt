package com.depi.ecosphere

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Onboarding03 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_onboarding03)

        // تأكيد وجود root = R.id.main قبل تطبيق الـ insets
        findViewById<View>(R.id.main)?.let { root ->
            ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
                val sys = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(sys.left, sys.top, sys.right, sys.bottom)
                insets
            }
        }

        findViewById<View>(R.id.skipText)?.setOnClickListener {
            startActivity(Intent(this, SignUp::class.java))
            finish()
        }

        // Next → SignUp
        findViewById<View>(R.id.nextButton)?.setOnClickListener {
            startActivity(Intent(this, SignUp::class.java))
            finish()
        }
    }
}
