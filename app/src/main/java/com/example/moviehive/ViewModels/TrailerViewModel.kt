package com.example.moviehive.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviehive.Repositories.TrailerRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class TrailerViewModel @Inject constructor(private val trailerRepo: TrailerRepo): ViewModel(){
    private val _movieKey = MutableLiveData<String>()
    val movieKey : LiveData<String>
        get() = _movieKey
    fun getMovieKey(movieId:Int){
        viewModelScope.launch {
           val result =viewModelScope.async {
                trailerRepo.getMovieKey(movieId)
            }
            _movieKey.postValue(result.await())

        }
    }
}