package id.co.inaportempat.movielist.ui.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import id.co.inaportempat.movielist.R
import id.co.inaportempat.movielist.data.remote.MovieService.makeService
import id.co.inaportempat.movielist.data.repository.MovieRepository
import id.co.inaportempat.movielist.model.Movie
import id.co.inaportempat.movielist.ui.adapter.MovieRvAdapter
import kotlinx.android.synthetic.main.fragment_popular_movies.*
import id.co.inaportempat.movielist.data.local.MovieDatabase


class PopularMoviesFragment : Fragment() {

    private lateinit var movieRvAdapter: MovieRvAdapter
    private lateinit var movieList: MutableList<Movie>
    private lateinit var movieViewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_popular_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        showLoading()
        showMovies()
    }

    private fun initView() {
        val movieRepository = MovieRepository(makeService(), MovieDatabase.getInstance(requireActivity()))
        movieViewModel =
            ViewModelProviders.of(requireActivity(), MovieViewModelFactory(movieRepository))
                .get(MovieViewModel::class.java)
        movieList = mutableListOf()
        movieRvAdapter = MovieRvAdapter(movieList)
        rvMovies.apply {
            //layoutManager = LinearLayoutManager(requireActivity())
            layoutManager = GridLayoutManager(requireActivity(), 2)
            setHasFixedSize(true)
            adapter = movieRvAdapter
        }
    }

    private fun showMovies() {
        movieViewModel.getPopularMovies().observe(
            this, Observer {
               it.map {
                   movieList.add(it)
                   movieRvAdapter.notifyDataSetChanged()
               }
            }
        )
    }

    private fun showLoading() {
        movieViewModel.isLoading.observe(this, Observer {
            if (it)
                loadingBar.visibility = View.VISIBLE
            else
                loadingBar.visibility = View.GONE
        })
    }
}