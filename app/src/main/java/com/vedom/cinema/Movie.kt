package com.vedom.cinema

import androidx.annotation.DrawableRes

data class Movie(
    val id: Long,
    val pg: Int,
    val isLiked: Boolean = false,
    @DrawableRes
    val posterImage: Int? = null,
    @DrawableRes
    val coverImage: Int? = null,
    val rating: Float,
    val reViews: Int,
    val title: String,
    val length: Int = 0,
    val tags: List<String>,
    val actorInfoList: List<Actor>? = null,
    val storyLine: String = ""
)