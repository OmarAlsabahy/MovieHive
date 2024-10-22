package com.example.moviehive.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviehive.Api.Models.MovieDetailsModel
import com.example.moviehive.Repositories.DetailsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class DetailsViewModel @Inject constructor(private val detailsRepo: DetailsRepo) : ViewModel() {
    private val _movie = MutableLiveData<MovieDetailsModel>()
    val movie:LiveData<MovieDetailsModel>
        get() = _movie
    fun getMovieById(movieId:Int){
        viewModelScope.launch {
            val result = viewModelScope.async {
                detailsRepo.getMovieById(movieId)
            }

            _movie.postValue(result.await())
        }

    }
}