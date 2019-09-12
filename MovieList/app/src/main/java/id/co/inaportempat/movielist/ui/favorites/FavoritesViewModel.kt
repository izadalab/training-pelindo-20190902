package id.co.inaportempat.movielist.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.co.inaportempat.movielist.data.repository.MovieRepository
import id.co.inaportempat.movielist.model.Movie

class FavoritesViewModel(private val repository: MovieRepository) : ViewModel() {
    val isLoading: MutableLiveData<Boolean> = repository.isLoading

    fun getFavoriteMovies(): LiveData<List<Movie>> = repository.getMovies("favorites")
}