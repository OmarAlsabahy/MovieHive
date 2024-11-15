package com.example.moviehive.Api

import com.example.moviehive.Api.Models.MovieDetailsModel
import com.example.moviehive.Api.Models.MovieModel
import com.example.moviehive.Api.Models.MovieVideoModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/now_playing")
    suspend fun getNowShowingMovies(@Query("page")pageNumber:Int):MovieModel
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page")pageNumber:Int):MovieModel
    @GET("movie/{movie_id}")
    suspend fun getMovieById(@Path("movie_id")movieId:Int):MovieDetailsModel
    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideoById(@Path("movie_id")movieId: Int):MovieVideoModel
}