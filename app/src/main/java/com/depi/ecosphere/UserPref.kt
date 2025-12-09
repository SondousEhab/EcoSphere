package com.depi.ecosphere

import android.content.Context
import android.content.SharedPreferences

class UserPref(context: Context) {

    private val pref: SharedPreferences =
        context.getSharedPreferences("user_data", Context.MODE_PRIVATE)

    fun saveUser(email: String, password: String, username: String) {
        pref.edit().apply {
            putString("email", email)
            putString("password", password)
            putString("username", username)
            putBoolean("logged_in", true)
            putInt("points", 0)
            putInt("completed_challenges", 0)
            apply()
        }
    }

    fun loginSuccess(email: String, password: String): Boolean {
        val savedEmail = pref.getString("email", null)
        val savedPassword = pref.getString("password", null)
        return email == savedEmail && password == savedPassword
    }

    fun isLoggedIn(): Boolean {
        return pref.getBoolean("logged_in", false)
    }

    fun setLoggedIn(value: Boolean) {
        pref.edit().apply {
            putBoolean("logged_in", value)
            apply()
        }
    }

    fun logout() {
        pref.edit().apply {
            putBoolean("logged_in", false)
            apply()
        }
    }

    fun getUsername(): String {
        return pref.getString("username", "User") ?: "User"
    }

    fun getPoints(): Int {
        return pref.getInt("points", 0)
    }

    fun setPoints(value: Int) {
        pref.edit().apply {
            putInt("points", value)
            apply()
        }
    }

    fun addPoints(delta: Int) {
        val current = getPoints()
        setPoints(current + delta)
    }

    fun getCompletedChallenges(): Int {
        return pref.getInt("completed_challenges", 0)
    }

    fun addCompletedChallenge() {
        val current = getCompletedChallenges()
        pref.edit().apply {
            putInt("completed_challenges", current + 1)
            apply()
        }
    }
}
