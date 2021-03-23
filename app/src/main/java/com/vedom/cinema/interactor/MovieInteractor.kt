package com.vedom.cinema.interactor

import android.content.Context
import com.vedom.cinema.models.data.Movie
import com.vedom.cinema.models.data.loadMovies
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class MovieInteractor(
    private val dispatcher: CoroutineDispatcher,
    private val context: Context
    ) {
    suspend fun getDataMovies(): List<Movie> = withContext(dispatcher) {
        loadMovies(context = context)
    }
}