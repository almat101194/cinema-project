package com.vedom.cinema.pojo.credits


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreditsResponse(
    @SerialName("cast")
    val cast: List<Cast>,
    @SerialName("crew")
    val crew: List<Crew>,
    @SerialName("id")
    val id: Int
)