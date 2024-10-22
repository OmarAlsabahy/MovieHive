package com.example.moviehive.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviehive.Api.Models.MovieResult
import com.example.moviehive.Repositories.HomeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepo: HomeRepo) : ViewModel() {
    private val _showingMovies = MutableLiveData<List<MovieResult>>()
    val showingMovies = _showingMovies
    private val _popularMovies = MutableLiveData<List<MovieResult>>()
    val popularMovies = _popularMovies

    fun getNowShowing(){
        viewModelScope.launch {
            val result = viewModelScope.async {
                val resultMovies = homeRepo.getNowShowingMovies()
                resultMovies.results
            }
            _showingMovies.value = result.await()
        }

    }

    fun getPopular(){
        viewModelScope.launch {
            val result = viewModelScope.async {
                val movieResults = homeRepo.getPopularMovies()
                movieResults.results
            }

            _popularMovies.value = result.await()

        }
    }


}