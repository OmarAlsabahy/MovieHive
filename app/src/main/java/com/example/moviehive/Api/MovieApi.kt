package com.example.moviehive.Api

import com.example.moviehive.Api.Models.MovieModel
import retrofit2.http.GET

interface MovieApi {
    @GET("movie/now_playing")
    suspend fun getNowShowingMovies():MovieModel
    @GET("movie/popular")
    suspend fun getPopularMovies():MovieModel
}