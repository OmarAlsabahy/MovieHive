package com.example.moviehive.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviehive.Api.Models.MovieModel
import com.example.moviehive.Api.Models.MovieResult
import com.example.moviehive.Repositories.HomeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepo: HomeRepo) : ViewModel() {
    private val _showingMovies = MutableLiveData<List<MovieResult>>()
    val showingMovies :LiveData<List<MovieResult>>
        get() = _showingMovies
    private val _popularMovies = MutableLiveData<List<MovieResult>>()
    val popularMovies:LiveData<List<MovieResult>>
        get() = _popularMovies
    private var showingMoviesData = mutableListOf<MovieResult>()
    private var popularMoviesData = mutableListOf<MovieResult>()

    private companion object{
        var popularPageNumber = 1
        var nowShowingPageNumber = 1
    }
    fun getNowShowing(){
        viewModelScope.launch {
            val result = viewModelScope.async {
               homeRepo.getNowShowingMovies(nowShowingPageNumber)
            }.await().results
            showingMoviesData.addAll(result)
            _showingMovies.postValue(showingMoviesData)
        }

    }

    fun getPopular(){
        viewModelScope.launch {
            val result = viewModelScope.async {
                 homeRepo.getPopularMovies(popularPageNumber)
            }.await().results
            popularMoviesData.addAll(result)
            _popularMovies.postValue(popularMoviesData)
        }
    }

    fun increasePopularPageNumber(){
        popularPageNumber+=1
    }
    fun increaseNowShowingPageNumber(){
        nowShowingPageNumber+=1
    }

}