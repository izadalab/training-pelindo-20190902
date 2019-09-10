package id.co.inaportempat.movielist.ui.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.co.inaportempat.movielist.data.remote.MovieApiEndpoint

class MovieViewModelFactory(private val api: MovieApiEndpoint) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(api) as T
    }
}