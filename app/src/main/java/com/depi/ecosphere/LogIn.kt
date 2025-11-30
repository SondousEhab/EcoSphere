package com.depi.ecosphere

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class LogIn : AppCompatActivity() {

    private lateinit var pref: UserPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_log_in)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        pref = UserPref(this)

        val emailInput = findViewById<TextInputEditText>(R.id.EmailLogin)
        val passwordInput = findViewById<TextInputEditText>(R.id.PasswordLogin)
        val loginButton = findViewById<MaterialButton>(R.id.btnLogin)
        val signupNowText = findViewById<TextView>(R.id.SignupNow)

        loginButton.setOnClickListener {
            val email = emailInput.text?.toString()?.trim() ?: ""
            val password = passwordInput.text?.toString()?.trim() ?: ""

            if (!validateInput(email, password)) return@setOnClickListener

            if (pref.loginSuccess(email, password)) {
                pref.setLoggedIn(true)      // 👈 دي أهم سطر
                toast("Logged in successfully")
                goToMain()
            } else {
                toast("Incorrect email or password")
            }

        }

        signupNowText.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        if (pref.isLoggedIn()) {
            goToMain()
        }
    }

    private fun validateInput(email: String, password: String): Boolean {
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            toast("Please enter a valid email")
            return false
        }

        if (password.isEmpty()) {
            toast("Please enter your password")
            return false
        }

        return true
    }

    private fun goToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
