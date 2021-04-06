package com.vedom.cinema.viewmodeles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vedom.cinema.interactor.MovieInteractor
import com.vedom.cinema.models.data.Movie
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MoviesListViewModel(private val interactor: MovieInteractor)
    : ViewModel() {

        private val _movieList = MutableLiveData<List<Movie>>(emptyList())
        val movieList: LiveData<List<Movie>> = _movieList

        private val _state = MutableLiveData<Boolean>()
        val state: LiveData<Boolean> = _state

        fun loadMovies() {
            viewModelScope.launch {
                _state.value = true
//                delay(2000)
                _state.value = false
                _movieList.value = interactor.getDataMovies()

            }
        }
}