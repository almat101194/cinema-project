package com.vedom.cinema.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vedom.cinema.R
import com.vedom.cinema.models.data.Actor
import com.vedom.cinema.utils.imageOption

class ActorsListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    private val name: TextView = itemView.findViewById(R.id.tv_actor_name)
    private val photoActor: ImageView = itemView.findViewById(R.id.iv_actor_photo)

    fun bind(actor: Actor) {
        name.text = actor.name
        setActorPhoto(actor)
    }

    private fun setActorPhoto(actor: Actor) {
        Glide.with(context)
                .clear(photoActor)
        Glide.with(context)
                .load(actor.picture)
                .apply(imageOption)
                .into(photoActor)

    }
}
private val RecyclerView.ViewHolder.context
    get() = this.itemView.context