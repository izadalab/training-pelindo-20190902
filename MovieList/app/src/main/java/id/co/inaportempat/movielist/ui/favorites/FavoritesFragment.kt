package id.co.inaportempat.movielist.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import id.co.inaportempat.movielist.R
import id.co.inaportempat.movielist.data.local.MovieDatabase
import id.co.inaportempat.movielist.data.remote.MovieService.makeService
import id.co.inaportempat.movielist.data.repository.MovieRepository
import id.co.inaportempat.movielist.ui.adapter.MovieRvAdapter
import kotlinx.android.synthetic.main.fragment_popular_movies.*

class FavoritesFragment : Fragment() {

    private lateinit var movieRvAdapter: MovieRvAdapter
    private lateinit var favoritesViewModel: FavoritesViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_popular_movies, container, false)
    }

    override fun onResume() {
        super.onResume()
        initView()
        showFavorites()
    }

    private fun initView() {
        val movieRepository = MovieRepository(
            makeService(),
            MovieDatabase.getInstance(requireActivity())
        )
        favoritesViewModel = ViewModelProviders.of(
            requireActivity(), FavoriteViewModelFactory(movieRepository)
        ).get(FavoritesViewModel::class.java)

        rvMovies.apply {
            layoutManager = GridLayoutManager(requireActivity(), 2)
            setHasFixedSize(true)

        }
    }


    private fun showFavorites() {
        favoritesViewModel.getFavoriteMovies().observe(this, Observer {
            movieRvAdapter = MovieRvAdapter(it)
            rvMovies.adapter = movieRvAdapter

        })
    }

}