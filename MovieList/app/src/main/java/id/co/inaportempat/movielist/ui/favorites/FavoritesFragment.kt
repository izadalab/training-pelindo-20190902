package id.co.inaportempat.movielist.ui.favorites

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import id.co.inaportempat.movielist.R
import id.co.inaportempat.movielist.data.local.MovieDatabase.Companion.getInstance
import id.co.inaportempat.movielist.model.Movie
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
        favoritesViewModel.getFavoriteMovies()
    }

    private fun initView() {
        favoritesViewModel = ViewModelProviders.of(
            requireActivity(), FavoriteViewModelFactory(
                getInstance(requireContext())
            )
        ).get(FavoritesViewModel::class.java)
    }


    private fun showFavorites() {
        favoritesViewModel.moviesLiveData.observe(this, Observer {
            movieRvAdapter = MovieRvAdapter(it)
            rvMovies.apply {
                //layoutManager = LinearLayoutManager(requireActivity())
                layoutManager = GridLayoutManager(requireActivity(), 2)
                setHasFixedSize(true)
                adapter = movieRvAdapter
            }

        })
    }

}