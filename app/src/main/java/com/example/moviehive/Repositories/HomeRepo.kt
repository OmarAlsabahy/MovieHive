package com.example.moviehive.Repositories

import com.example.moviehive.Api.MovieApi
import javax.inject.Inject

class HomeRepo @Inject constructor(private val nowShowingApi: MovieApi) {
    suspend fun getNowShowingMovies() = nowShowingApi.getNowShowingMovies()
    suspend fun getPopularMovies() = nowShowingApi.getPopularMovies()
}