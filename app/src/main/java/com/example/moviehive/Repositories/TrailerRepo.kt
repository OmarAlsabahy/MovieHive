package com.example.moviehive.Repositories
import com.example.moviehive.Api.MovieApi
import javax.inject.Inject

class TrailerRepo @Inject constructor(private val movieApi: MovieApi) {
    suspend fun getMovieKey(movieId:Int): String {
        val movie = movieApi.getMovieVideoById(movieId)
        return movie.results[0].key
    }
}