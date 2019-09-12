package id.co.inaportempat.movielist.ui.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.co.inaportempat.movielist.data.repository.MovieRepository
import id.co.inaportempat.movielist.model.Movie

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

    val isLoading: LiveData<Boolean> = repository.isLoading

    fun getPopularMovies(): LiveData<List<Movie>> =
        repository.getMovies("popular")


}