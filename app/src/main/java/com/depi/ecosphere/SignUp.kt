package com.depi.ecosphere

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class SignUp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val pref = UserPref(this)

        val usernameInput = findViewById<TextInputEditText>(R.id.Username)
        val emailInput = findViewById<TextInputEditText>(R.id.etEmail)
        val passwordInput = findViewById<TextInputEditText>(R.id.Password)
        val confirmInput = findViewById<TextInputEditText>(R.id.Confirm)
        val signUpButton = findViewById<MaterialButton>(R.id.btnSignup)
        val haveAccountText = findViewById<android.widget.TextView>(R.id.HaveAccount)

        signUpButton.setOnClickListener {
            val name = usernameInput.text?.toString()?.trim() ?: ""
            val email = emailInput.text?.toString()?.trim() ?: ""
            val password = passwordInput.text?.toString()?.trim() ?: ""
            val confirm = confirmInput.text?.toString()?.trim() ?: ""

            if (!validateInput(name, email, password, confirm)) return@setOnClickListener

            // نحفظ اليوزر محلي
            pref.saveUser(email, password, name)
            toast("Account created!")
            goToMain()
        }

        haveAccountText.setOnClickListener {
            startActivity(Intent(this, LogIn::class.java))
            finish()
        }
    }

    private fun validateInput(
        name: String,
        email: String,
        password: String,
        confirm: String
    ): Boolean {

        if (name.isEmpty()) {
            toast("Please enter a username")
            return false
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            toast("Please enter a valid email")
            return false
        }

        if (password.length < 6) {
            toast("Password must be at least 6 characters")
            return false
        }

        if (password != confirm) {
            toast("Passwords do not match")
            return false
        }

        return true
    }

    private fun goToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}
