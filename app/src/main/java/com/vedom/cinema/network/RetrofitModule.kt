package com.vedom.cinema.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.vedom.cinema.network.api.MovieApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create

object RetrofitModule {
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY = "d0f56f91dd27c3c04bf790af05cef36c"
    const val PARAM_API_KEY = "api_key"
    const val PARAM_LANGUAGE = "language"
    const val DEFAULT_LANGUAGE = "en-US"
    const val PARAM_PAGE = "page"
    const val PARAM_QUERY = "query"

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    private val client = OkHttpClient()
            .newBuilder()
            .addInterceptor(interceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .client(client)
            .build()

    val apiMovies: MovieApi = retrofit.create()
}