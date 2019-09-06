package id.co.inaportempat.movielist.ui.popular

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import id.co.inaportempat.movielist.R
import id.co.inaportempat.movielist.data.remote.MovieService.makeService
import id.co.inaportempat.movielist.model.Movie
import id.co.inaportempat.movielist.model.MovieResponse
import id.co.inaportempat.movielist.ui.adapter.MovieRvAdapter
import kotlinx.android.synthetic.main.fragment_popular_movies.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PopularMoviesFragment : Fragment() {

    private lateinit var movieRvAdapter: MovieRvAdapter
    private lateinit var movieList: MutableList<Movie>

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
        movieList = mutableListOf()
        movieRvAdapter = MovieRvAdapter(movieList)
        rvMovies.apply {
            //layoutManager = LinearLayoutManager(requireActivity())
            layoutManager = GridLayoutManager(requireActivity(), 2)
            setHasFixedSize(true)
            adapter = movieRvAdapter
        }
        fetchPopularMovies()
    }

    private fun fetchPopularMovies() {
        val service = makeService()
        service.getPopularMovies("678ef42a1b584848591cbd02ac3899c3").enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e(
                    "Error Message", t.localizedMessage
                )
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    val movieResponse = response.body()
                    movieResponse?.results?.map { movie ->
                        movieList.add(movie)
                        movieRvAdapter.notifyDataSetChanged()
                        Log.d("movies", movie.title)
                    }
                } else {
                    Log.e("movies", "get response failed")
                }
            }
        })
    }
}