package com.vedom.cinema

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ActorsListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    private val name: TextView = itemView.findViewById(R.id.tv_actor_name)
    private val photoActor: ImageView = itemView.findViewById(R.id.iv_actor_photo)

    fun bind(actor: Actor) {
        name.text = actor.name
        actor.image?.let { photoActor.setImageResource(it) }
    }
}