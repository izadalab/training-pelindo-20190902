package id.co.inaportempat.movielist.ui.favorites

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.co.inaportempat.movielist.data.local.MovieDatabase
import id.co.inaportempat.movielist.model.Movie

class FavoritesViewModel(private val database: MovieDatabase) : ViewModel() {
    val moviesLiveData: MutableLiveData<List<Movie>> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun getFavoriteMovies() {
        val favoriteMovies = database.movieDao().getMovies()

        Log.i("movies", favoriteMovies.size.toString())

        if(favoriteMovies.isNotEmpty()) {
            moviesLiveData.postValue(favoriteMovies)
        } else {
            moviesLiveData.postValue(listOf())
        }
    }
}