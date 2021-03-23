package com.vedom.cinema.another

import androidx.annotation.DrawableRes

data class Actor(
    val id: Int,
    val name: String?,
    @DrawableRes
    val image: Int?
)