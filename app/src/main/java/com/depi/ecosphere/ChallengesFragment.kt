package com.depi.ecosphere

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ChallengesFragment : Fragment(R.layout.fragment_challenges) {

    private val sharedViewModel: SharedChallengesViewModel by activityViewModels()
    private lateinit var adapter: ChallengesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.loadFromPrefs(requireContext())

        val recyclerView = view.findViewById<RecyclerView>(R.id.challengesRecyclerView)


        adapter = ChallengesAdapter(
            emptyList(),
            onClick = { challenge ->
                showChallengeDialog(challenge)
            },
            isProfileList = false
        )

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter


        sharedViewModel.profileChallenges.observe(viewLifecycleOwner) { profileList ->
            updateAvailableChallenges(profileList)
        }


        updateAvailableChallenges(sharedViewModel.profileChallenges.value ?: emptyList())
    }

    private fun updateAvailableChallenges(profileList: List<Challenge>) {

        val allChallenges = ChallengeData.challenges


        val availableChallenges = allChallenges.filterNot { profileChallenge ->
            profileList.any { it.title == profileChallenge.title }
        }

        adapter.updateItems(availableChallenges)
    }

    private fun showChallengeDialog(challenge: Challenge) {
        AlertDialog.Builder(requireContext())
            .setTitle(challenge.title)
            .setMessage(challenge.description)
            .setPositiveButton("Add to profile") { _, _ ->
                sharedViewModel.addToProfile(challenge, requireContext())
                Toast.makeText(
                    requireContext(),
                    "Added \"${challenge.title}\" to profile ✅",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .setNegativeButton("Close", null)
            .show()
    }
}
