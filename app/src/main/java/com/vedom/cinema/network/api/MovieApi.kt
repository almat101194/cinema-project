package com.vedom.cinema.network.api

import com.vedom.cinema.network.RetrofitModule.API_KEY
import com.vedom.cinema.network.RetrofitModule.DEFAULT_LANGUAGE
import com.vedom.cinema.network.RetrofitModule.PARAM_API_KEY
import com.vedom.cinema.network.RetrofitModule.PARAM_LANGUAGE
import com.vedom.cinema.network.RetrofitModule.PARAM_PAGE
import com.vedom.cinema.network.RetrofitModule.PARAM_QUERY
import com.vedom.cinema.pojo.configuration.ConfigurationResponse
import com.vedom.cinema.pojo.credits.CreditsResponse
import com.vedom.cinema.pojo.genre.GenreResponse
import com.vedom.cinema.pojo.nowplaying.NowplayingResponse
import com.vedom.cinema.pojo.popular.PopularResponse
import com.vedom.cinema.pojo.search.SearchResponse
import com.vedom.cinema.pojo.toprated.TopratedResponse
import com.vedom.cinema.pojo.upcoming.UpcomingResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("configuration")
    suspend fun getConfiguration(
            @Query(PARAM_API_KEY) apiKey: String = API_KEY
    ): ConfigurationResponse

    @GET("movie/popular")
    suspend fun getPopularMovies(
            @Query(PARAM_API_KEY) apiKey: String = API_KEY,
            @Query(PARAM_LANGUAGE) language: String = DEFAULT_LANGUAGE,
            @Query(PARAM_PAGE) page: String
    ): PopularResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
            @Query(PARAM_API_KEY) apiKey: String = API_KEY,
            @Query(PARAM_LANGUAGE) language: String = DEFAULT_LANGUAGE,
            @Query(PARAM_PAGE) page: String
    ): NowplayingResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
            @Query(PARAM_API_KEY) apiKey: String = API_KEY,
            @Query(PARAM_LANGUAGE) language: String = DEFAULT_LANGUAGE,
            @Query(PARAM_PAGE) page: String
    ): UpcomingResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
            @Query(PARAM_API_KEY) apiKey: String = API_KEY,
            @Query(PARAM_LANGUAGE) language: String = DEFAULT_LANGUAGE,
            @Query(PARAM_PAGE) page: String
    ): TopratedResponse

    @GET("movie/{movie_id}")
    suspend fun getDetailInfo(
            @Path("movie_id") movieId: String,
            @Query(PARAM_API_KEY) apiKey: String = API_KEY,
            @Query(PARAM_LANGUAGE) language: String = DEFAULT_LANGUAGE
    )

    @GET("genre/movie/list")
    suspend fun getGenres(
            @Query(PARAM_API_KEY) apiKey: String = API_KEY,
            @Query(PARAM_LANGUAGE) language: String = DEFAULT_LANGUAGE
    ): GenreResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getCreditsMovies(
            @Path("movie_id") movieId: String,
            @Query(PARAM_API_KEY) apiKey: String = API_KEY,
            @Query(PARAM_LANGUAGE) language: String = DEFAULT_LANGUAGE
    ): CreditsResponse

    @GET("search/movie")
    suspend fun getSearchMovie(
            @Query(PARAM_API_KEY) apiKey: String = API_KEY,
            @Query(PARAM_LANGUAGE) language: String = DEFAULT_LANGUAGE,
            @Query(PARAM_PAGE) page: String,
            @Query(PARAM_QUERY) query: String
    ): SearchResponse
}