package com.vedom.cinema.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vedom.cinema.interactor.MovieInteractor
import com.vedom.cinema.viewmodeles.MoviesDetailsViewModel
import com.vedom.cinema.viewmodeles.MoviesListViewModel
import kotlinx.coroutines.Dispatchers
import java.lang.IllegalArgumentException

class ViewModelFactory(val context: Context): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when(modelClass) {
        MoviesListViewModel::class.java -> MoviesListViewModel(
            interactor = MovieInteractor(
                dispatcher = Dispatchers.Default,
                context = context
            )
        )
        MoviesDetailsViewModel::class.java -> MoviesDetailsViewModel()
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}