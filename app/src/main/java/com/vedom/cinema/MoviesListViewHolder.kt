package com.vedom.cinema

import android.media.Image
import android.text.method.TextKeyListener.clear
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

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
        pg.text = "${movie.pg}+"
        genres.text = /*"${movie.tags[0]}, ${movie.tags[1]}, ${movie.tags[2]}"*/ movie.tags.joinToString(separator = ",")
        reviews.text = "${movie.reViews} reviews"
        runningTIme.text = "${movie.length} MINUTE"
        name.text = movie.title
        setLiked(movie.isLiked)
        rb.rating = movie.rating
        setPoster(movie)

    }

    private fun setPoster(movie: Movie) {
        ivPoster.apply {
            movie.posterImage?.let { setImageResource(it) }
        }
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