package com.vedom.cinema.pojo.configuration


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Images(
        @SerialName("backdrop_sizes")
    val backdropSizes: @RawValue List<String>,
        @SerialName("base_url")
    val baseUrl: String,
        @SerialName("logo_sizes")
    val logoSizes: @RawValue List<String>,
        @SerialName("poster_sizes")
    val posterSizes: @RawValue List<String>,
        @SerialName("profile_sizes")
    val profileSizes: @RawValue List<String>,
        @SerialName("secure_base_url")
    val secureBaseUrl: String,
        @SerialName("still_sizes")
    val stillSizes: @RawValue List<String>
): Parcelable