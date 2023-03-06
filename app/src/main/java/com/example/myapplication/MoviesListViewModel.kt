package com.example.myapplication

import android.app.Application
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.sql.DataSource
import kotlin.random.Random

const val API_KEY = "7bfe007798875393b05c5aa1ba26323e"
class MoviesListViewModel(val app: Application)  :  AndroidViewModel(app) {
    init {
        getMostPopularMovies()

    }
    private val _properties = MutableLiveData<List<MovieDataClass>>()
    val properties: LiveData<List<MovieDataClass>> get() = _properties
    val likedMovies: MutableList<LikedMoviesDataClass> = mutableListOf()
    fun getMostPopularMovies(){
        viewModelScope.launch {
            val resultList = MovieApi.retrofitService.getMoviesNowPlaying(API_KEY)
            Log.d("results", "the results $resultList")
            _properties.value = resultList.results
            for (property in resultList.results){
                val item = LikedMoviesDataClass(property.id, false)
                likedMovies.add(item)
            }
            resultList
        }
    }
    fun observeMovieLiveData(): LiveData<List<MovieDataClass>>{
        return properties
    }

     fun addLikedMovieToDatabase(movie: MovieDataClass){
        viewModelScope.launch{
            var updatedMovie = LikedMoviesDataClass("", false)
                for (movieItem in likedMovies) {
                    if (movieItem.movieId == movie.id) {
                        likedMovies.remove(movieItem)
                        updatedMovie = if (movieItem.enabled) {
                            LikedMoviesDataClass(movieItem.movieId, false)
                        } else {
                            LikedMoviesDataClass(movieItem.movieId, true)
                        }
                    }
                }
                likedMovies.add(updatedMovie)
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