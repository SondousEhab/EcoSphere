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

        // إعداد listener لتحديد الفراجمنت عند التبديل
        bottomNav.setOnNavigationItemSelectedListener { menuItem ->
            val fragment: Fragment = when(menuItem.itemId) {
                R.id.nav_home -> HomeFragment()
                R.id.nav_challenges -> ChallengesFragment()
                R.id.nav_profile -> ProfileFragment()
                else -> HomeFragment()  // الافتراضي
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .commit()
            true
        }

        // تأكد من اختيار الصفحة الافتراضية عند بدء التطبيق
        if (savedInstanceState == null) {
            bottomNav.selectedItemId = R.id.nav_home
        }
    }
}
