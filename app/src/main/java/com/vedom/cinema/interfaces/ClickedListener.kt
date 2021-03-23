package com.vedom.cinema.interfaces

import com.vedom.cinema.models.data.Movie

interface ClickedListener {
    fun onClick(movie: Movie)
}