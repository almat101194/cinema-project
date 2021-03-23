package com.vedom.cinema.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vedom.cinema.R
import com.vedom.cinema.holders.ActorsListViewHolder
import com.vedom.cinema.models.data.Actor

class ActorsListAdapter: RecyclerView.Adapter<ActorsListViewHolder>() {

    private var actors = listOf<com.vedom.cinema.models.data.Actor>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsListViewHolder {
        return ActorsListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_actor, parent, false))
    }

    override fun onBindViewHolder(holder: ActorsListViewHolder, position: Int) {
        holder.bind(actors[position])
    }

    override fun getItemCount(): Int = actors.size

    fun getItem(position: Int): Actor = actors[position]

    fun bindActors(newActors: List<com.vedom.cinema.models.data.Actor>) {
        actors = newActors
        notifyDataSetChanged()
    }
}