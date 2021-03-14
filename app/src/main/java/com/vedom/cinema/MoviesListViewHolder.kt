package com.vedom.cinema

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vedom.cinema.data.Movie

class MoviesListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val pg: TextView = itemView.findViewById(R.id.tv_view_holder_movie_pg)
    private val genres: TextView = itemView.findViewById(R.id.tv_view_holder_movie_genre)
    private val reviews: TextView = itemView.findViewById(R.id.tv_view_holder_movie_reviews)
    private val rb: RatingBar = itemView.findViewById(R.id.rb_view_holder_movie_list)
    private val name: TextView = itemView.findViewById(R.id.tv_view_holder_movie_name)
    private val runningTIme: TextView = itemView.findViewById(R.id.tv_view_holder_movie_running_time)
    private val ivIsLiked: ImageView = itemView.findViewById(R.id.iv_view_holder_movielike_box)
    private val ivPoster: ImageView = itemView.findViewById(R.id.iv_image_view_holder_movies_list)

    fun bind(movie: Movie) {
        pg.text = "${movie.minimumAge}+"
        genres.text = movie.genres.joinToString(separator = ","){it.name}
        reviews.text = "${movie.numberOfRatings} reviews"
        runningTIme.text = "${movie.runtime} MINUTE"
        name.text = movie.title
//        setLiked(movie)
        rb.rating = movie.ratings/2
        setPoster(movie)

    }

    private fun setPoster(movie: Movie) {


        Glide.with(context)
            .load(movie.poster)
            .into(ivPoster)

//        ivPoster.apply {
//
////            movie.posterImage?.let { setImageResource(it) }
//        }
    }

    private fun setLiked(isLiked: Boolean) {
        when {
            isLiked -> ivIsLiked.setImageDrawable(
                ContextCompat.getDrawable(
                    this.itemView.context,
                    R.drawable.truelike
                )
            )
            else -> ivIsLiked.setImageDrawable(
                ContextCompat.getDrawable(
                    this.itemView.context,
                    R.drawable.heart
                )
            )
        }
    }

}
private val RecyclerView.ViewHolder.context
    get() = this.itemView.context