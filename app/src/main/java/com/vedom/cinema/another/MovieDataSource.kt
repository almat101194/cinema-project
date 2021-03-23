package com.vedom.cinema.another

import com.vedom.cinema.R

class MovieDataSource {
    fun getMovies():List<Movie> {
        return listOf(
            Movie(
                id = 1,
                pg = 13,
                isLiked = false,
                posterImage = R.drawable.movie_avanger_end_game,
                coverImage = R.drawable.orig,
                rating = 4.0F,
                reViews = 125,
                title = "Avengers: End Game",
                length = 137,
                tags = listOf("Action", "Adventure", "Drama"),
                actorInfoList = ActorDataSource().getActors(),
                storyLine = "After the devastating events of Avengers: Infinity War, the universe is "
                        + "in ruins. With the help of remaining allies, the Avengers assemble once "
                        + "more in order to reverse Thanos\\' actions and restore balance to the universe."
            ),
            Movie(
                id = 2,
                pg = 16,
                isLiked = true,
                posterImage = R.drawable.movie_poster_tenet,
                coverImage = R.drawable.orig,
                rating = 5.0F,
                reViews = 98,
                title = "Tenet",
                length = 97,
                tags = listOf("Action", "Sci-Fi", "Thriller")

            ),
            Movie(
                id = 3,
                pg = 13,
                isLiked = false,
                posterImage = R.drawable.movie_poster_black_widow,
                coverImage = R.drawable.orig,
                rating = 4.0F,
                reViews = 38,
                title = "Black Widow",
                length = 102,
                tags = listOf("Action", "Adventure", "Sci-Fi")
            ),
            Movie(
                id = 4,
                pg = 13,
                isLiked = false,
                posterImage = R.drawable.movie_poster_wonder_woman,
                coverImage = R.drawable.orig,
                rating = 5.0F,
                reViews = 74,
                title = "Wonder Woman 1984",
                length = 120,
                tags = listOf("Action", "Adventure", "Fantasy")
            )
        )
    }
}