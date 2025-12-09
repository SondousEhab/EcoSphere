package com.depi.ecosphere

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChallengesAdapter(
    private var items: List<Challenge>,
    private val onClick: (Challenge) -> Unit,
    private val isProfileList: Boolean
) : RecyclerView.Adapter<ChallengesAdapter.ChallengeViewHolder>() {

    class ChallengeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitle: TextView = itemView.findViewById(R.id.txtTitle)
        val txtDescription: TextView = itemView.findViewById(R.id.txtDescription)
        val imgButton: ImageView = itemView.findViewById(R.id.imgButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_challenge, parent, false)
        return ChallengeViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChallengeViewHolder, position: Int) {
        val item = items[position]

        holder.txtTitle.text = item.title
        holder.txtDescription.text = item.description


        if (isProfileList) {

            holder.imgButton.setImageResource(R.drawable.ic_check)
        } else {
            holder.imgButton.setImageResource(R.drawable.buttom) // الأيقونة القديمة (+)
        }


        holder.itemView.setOnClickListener { onClick(item) }


        holder.imgButton.setOnClickListener { onClick(item) }
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(newItems: List<Challenge>) {
        items = newItems
        notifyDataSetChanged()
    }
}
