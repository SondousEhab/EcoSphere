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


        sharedViewModel.loadFromPrefs(requireContext())

        val treeImage = view.findViewById<ImageView>(R.id.treeImage)
        val pointsText = view.findViewById<TextView>(R.id.homePoints)


        sharedViewModel.points.observe(viewLifecycleOwner) { points ->

            pointsText.text = "Points: $points"


            when {
                points < 50 -> treeImage.setImageResource(R.drawable.tree_level1)
                points < 100 -> treeImage.setImageResource(R.drawable.tree_level2)
                else -> treeImage.setImageResource(R.drawable.tree_level3)
            }
        }
    }
}
