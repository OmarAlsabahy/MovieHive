package com.example.moviehive.Repositories

import com.example.moviehive.Api.MovieApi
import javax.inject.Inject

class HomeRepo @Inject constructor(private val nowShowingApi: MovieApi) {
    suspend fun getNowShowingMovies(pageNumber:Int) = nowShowingApi.getNowShowingMovies(pageNumber)
    suspend fun getPopularMovies(pageNumber:Int) = nowShowingApi.getPopularMovies(pageNumber)
}