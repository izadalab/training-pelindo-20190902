package id.co.inaportempat.movielist.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.co.inaportempat.movielist.data.local.MovieDatabase
import id.co.inaportempat.movielist.data.remote.MovieApiEndpoint

class DetailMovieViewModelFactory(
    private val service: MovieApiEndpoint,
    private val database: MovieDatabase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailMovieViewModel(service, database) as T
    }
}