package id.co.inaportempat.movielist.ui.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import id.co.inaportempat.movielist.R
import id.co.inaportempat.movielist.model.Movie
import id.co.inaportempat.movielist.ui.adapter.MovieRvAdapter
import kotlinx.android.synthetic.main.fragment_popular_movies.*

class PopularMoviesFragment : Fragment() {

    private lateinit var movieRvAdapter: MovieRvAdapter
    private lateinit var movieList: List<Movie>

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
    }

    private fun initView() {
        movieList = generateMovies()
        movieRvAdapter = MovieRvAdapter(movieList)
        rvMovies.apply {
            //layoutManager = LinearLayoutManager(requireActivity())
            layoutManager = GridLayoutManager(requireActivity(),2 )
            setHasFixedSize(true)
            adapter = movieRvAdapter
        }
    }

    private fun generateMovies(): List<Movie> = listOf(
        Movie("A Star", R.drawable.poster_a_star, ""),
        Movie("Aquaman", R.drawable.poster_aquaman, ""),
        Movie("Avenger Infinity", R.drawable.poster_avengerinfinity, ""),
        Movie("Deadpool", R.drawable.poster_deadpool, ""),
        Movie("Dragon Ball", R.drawable.poster_dragonball, "")
    )
}