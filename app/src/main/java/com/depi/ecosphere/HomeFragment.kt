package com.depi.ecosphere

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val sharedViewModel: SharedChallengesViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // تحميل الداتا المحفوظة من الـ SharedPreferences
        sharedViewModel.loadFromPrefs(requireContext())

        val treeImage = view.findViewById<ImageView>(R.id.treeImage)
        val pointsText = view.findViewById<TextView>(R.id.homePoints)

        // نربط الـ UI بالنقط
        sharedViewModel.points.observe(viewLifecycleOwner) { points ->
            // نص النقط تحت الشجرة
            pointsText.text = "Points: $points"

            // اختيار صورة الشجرة حسب عدد النقط
            when {
                points < 50 -> treeImage.setImageResource(R.drawable.tree_level1)
                points < 100 -> treeImage.setImageResource(R.drawable.tree_level2)
                else -> treeImage.setImageResource(R.drawable.tree_level3)
            }
        }
    }
}
