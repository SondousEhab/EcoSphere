package com.depi.ecosphere

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val sharedViewModel: SharedChallengesViewModel by activityViewModels()
    private lateinit var adapter: ChallengesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        sharedViewModel.loadFromPrefs(requireContext())

        val recyclerView = view.findViewById<RecyclerView>(R.id.profileChallengesRecyclerView)
        val pointsText = view.findViewById<TextView>(R.id.points)
        val completedCountText = view.findViewById<TextView>(R.id.completedCount)

        adapter = ChallengesAdapter(
            emptyList(),
            onClick = { challenge ->
                showProfileChallengeDialog(challenge)
            },
            isProfileList = true
        )

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        // تحديث قائمة التحديات في البروفايل
        sharedViewModel.profileChallenges.observe(viewLifecycleOwner) { list ->
            adapter.updateItems(list)
        }

        // تحديث النقاط وعدد التحديات المكتملة
        sharedViewModel.points.observe(viewLifecycleOwner) { points ->
            pointsText.text = "Points: $points"
            val completedCount = points / 10
            completedCountText.text = completedCount.toString()
        }
    }

    private fun showProfileChallengeDialog(challenge: Challenge) {
        AlertDialog.Builder(requireContext())
            .setTitle(challenge.title)
            .setMessage(challenge.description)
            .setPositiveButton("Mark as complete") { _, _ ->
                sharedViewModel.markComplete(challenge, requireContext())
                Toast.makeText(
                    requireContext(),
                    "Marked \"${challenge.title}\" as complete ✅ (+10 points)",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .setNegativeButton("Delete") { _, _ ->
                sharedViewModel.removeFromProfile(challenge, requireContext())
                Toast.makeText(
                    requireContext(),
                    "Removed \"${challenge.title}\" from profile",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .show()
    }
}
