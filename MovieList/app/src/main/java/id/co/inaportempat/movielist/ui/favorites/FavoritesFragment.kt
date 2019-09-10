package id.co.inaportempat.movielist.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import id.co.inaportempat.movielist.R
import id.co.inaportempat.movielist.data.local.MovieDatabase
import id.co.inaportempat.movielist.model.Movie
import id.co.inaportempat.movielist.ui.adapter.MovieRvAdapter
import kotlinx.android.synthetic.main.fragment_popular_movies.*

class FavoritesFragment : Fragment() {

    private lateinit var movieList: MutableList<Movie>
    private lateinit var movieRvAdapter: MovieRvAdapter

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
        getFavoriteMovies()
    }

    private fun initView() {
        movieList = mutableListOf()
        movieRvAdapter = MovieRvAdapter(movieList)
        rvMovies.apply {
            //layoutManager = LinearLayoutManager(requireActivity())
            layoutManager = GridLayoutManager(requireActivity(), 2)
            setHasFixedSize(true)
            adapter = movieRvAdapter
        }
    }

    private fun getFavoriteMovies() {
        loadingBar.visibility = View.VISIBLE
        val movieDatabase = MovieDatabase.getInstance(requireActivity())
        val movieDao = movieDatabase.movieDao()

        val favoriteMovies = movieDao.getMovies()

        if (favoriteMovies.isNotEmpty()) {
            favoriteMovies.map { movie ->
                movieList.add(movie)
                movieRvAdapter.notifyDataSetChanged()
                loadingBar.visibility = View.GONE
            }
        } else {
                loadingBar.visibility = View.GONE
        }
    }

}