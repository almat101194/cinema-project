package com.vedom.cinema.pojo.configuration


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConfigurationResponse(
    @SerialName("change_keys")
    val changeKeys: List<String>,

    val images: Images
)