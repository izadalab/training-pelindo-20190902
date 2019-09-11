package id.co.inaportempat.movielist.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.co.inaportempat.movielist.data.local.MovieDatabase

class FavoriteViewModelFactory(private val database: MovieDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FavoritesViewModel(database) as T
    }
}