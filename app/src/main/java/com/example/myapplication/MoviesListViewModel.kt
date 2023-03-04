package com.example.myapplication

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import javax.sql.DataSource
import kotlin.random.Random

//
class MoviesListViewModel(val app: Application)  :  AndroidViewModel(app) {
    init {
        getMostPopularMovies()

    }
    private val _properties = MutableLiveData<List<MovieDataClass>>()
    val properties: LiveData<List<MovieDataClass>> get() = _properties
    fun getMostPopularMovies(){
        viewModelScope.launch {
            val resultList = MovieApi.retrofitService.getMoviesNowPlaying("7bfe007798875393b05c5aa1ba26323e")
            Log.d("results", "the results $resultList")
            _properties.value = resultList.results
        }
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MoviesListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MoviesListViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}