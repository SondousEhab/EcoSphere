package com.depi.ecosphere

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedChallengesViewModel : ViewModel() {

    private val _profileChallenges = MutableLiveData<List<Challenge>>(emptyList())
    val profileChallenges: LiveData<List<Challenge>> = _profileChallenges

    private val _points = MutableLiveData<Int>(0)
    val points: LiveData<Int> = _points

    companion object {
        private const val PREFS_NAME = "eco_prefs"
        private const val KEY_POINTS = "points"
        private const val KEY_PROFILE = "profile_challenges"
    }

    // استدعاء ده من الفراجمنت وقت الفتح
    fun loadFromPrefs(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        val savedPoints = prefs.getInt(KEY_POINTS, 0)
        _points.value = savedPoints

        val serialized = prefs.getString(KEY_PROFILE, null)
        if (!serialized.isNullOrEmpty()) {
            val list = serialized.split("||").mapNotNull { item ->
                val parts = item.split("##")
                if (parts.size == 2) {
                    Challenge(parts[0], parts[1])
                } else {
                    null
                }
            }
            _profileChallenges.value = list
        } else {
            _profileChallenges.value = emptyList()
        }
    }

    private fun saveToPrefs(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val challenges = _profileChallenges.value ?: emptyList()
        val serialized = challenges.joinToString("||") { "${it.title}##${it.description}" }

        prefs.edit()
            .putInt(KEY_POINTS, _points.value ?: 0)
            .putString(KEY_PROFILE, serialized)
            .apply()
    }

    fun addToProfile(challenge: Challenge, context: Context) {
        val current = _profileChallenges.value ?: emptyList()

        if (current.any { it.title == challenge.title }) return

        _profileChallenges.value = current + challenge
        saveToPrefs(context)
    }

    fun markComplete(challenge: Challenge, context: Context) {
        val current = _profileChallenges.value ?: emptyList()
        _profileChallenges.value = current.filter { it.title != challenge.title }

        val currentPoints = _points.value ?: 0
        _points.value = currentPoints + 10

        saveToPrefs(context)
    }

    fun removeFromProfile(challenge: Challenge, context: Context) {
        val current = _profileChallenges.value ?: emptyList()
        _profileChallenges.value = current.filter { it.title != challenge.title }

        saveToPrefs(context)
    }
}
