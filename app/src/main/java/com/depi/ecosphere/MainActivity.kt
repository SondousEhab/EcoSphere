package com.depi.ecosphere

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_nav)

        // أول ما الأبلكيشن يفتح نحط HomeFragment
        if (savedInstanceState == null) {
            openFragment(HomeFragment())
            bottomNav.selectedItemId = R.id.nav_home
        }

        // التبديل بين التابات بتاعة البوتوم ناف
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> openFragment(HomeFragment())
                R.id.nav_challenges -> openFragment(ChallengesFragment())
                R.id.nav_profile -> openFragment(ProfileFragment())
                else -> openFragment(HomeFragment())
            }
            true
        }
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
    }
}
