package com.example.moviehive.Repositories

import com.example.moviehive.Api.MovieApi
import javax.inject.Inject

class DetailsRepo @Inject constructor(private val movieApi: MovieApi) {
    suspend fun getMovieById(movieId:Int) = movieApi.getMovieById(movieId)
}