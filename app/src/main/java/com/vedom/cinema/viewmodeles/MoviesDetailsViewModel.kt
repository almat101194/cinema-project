package com.vedom.cinema.viewmodeles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vedom.cinema.models.data.Actor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MoviesDetailsViewModel: ViewModel() {

    private val _state = MutableLiveData<Boolean>()
    val state: LiveData<Boolean> = _state

    fun startLoading() {
        viewModelScope.launch {
            _state.value = true
            delay(2000)
            _state.value = false
        }
    }
}