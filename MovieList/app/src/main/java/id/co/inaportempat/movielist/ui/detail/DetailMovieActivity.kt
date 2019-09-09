package id.co.inaportempat.movielist.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import id.co.inaportempat.movielist.Constants.BACKDROP_URL
import id.co.inaportempat.movielist.Constants.POSTER_URL
import id.co.inaportempat.movielist.R
import id.co.inaportempat.movielist.data.local.MovieDao
import id.co.inaportempat.movielist.data.local.MovieDatabase
import id.co.inaportempat.movielist.data.remote.MovieService.makeService
import id.co.inaportempat.movielist.model.Movie
import id.co.inaportempat.movielist.model.Trailer
import id.co.inaportempat.movielist.model.TrailerResponse
import id.co.inaportempat.movielist.ui.adapter.TrailerRvAdapter
import kotlinx.android.synthetic.main.activity_detail_movie.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var trailerAdapter: TrailerRvAdapter
    private lateinit var trailerList: MutableList<Trailer>
    private lateinit var movieDatabase: MovieDatabase
    private lateinit var movieDao: MovieDao
    private lateinit var movie: Movie
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

//        val title = intent.getStringExtra("title")
//        val releaseDate = intent.getStringExtra("releaseDate")
//        val popularity = intent.getDoubleExtra("popularity", 0.0)

        movie = intent.getParcelableExtra("movieIntent")!!
        showDetailMovie(movie)
        supportActionBar?.title = movie.title

        isFavorite = isMovieFavorite(movie.id)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorite_menu, menu)
        if (isFavorite) {
            menu?.findItem(R.id.favoriteButton)?.icon = resources
                .getDrawable(R.drawable.ic_favorite_black_24dp)
        } else {
            menu?.findItem(R.id.favoriteButton)?.icon = resources
                .getDrawable(R.drawable.ic_favorite_border_black_24dp)
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favoriteButton -> {
                when (isFavorite) {
                    true -> {
                        removeFromFavorite(movie.id)
                        item.setIcon(R.drawable.ic_favorite_border_black_24dp)
                        isFavorite = false
                    }
                    false -> {
                        addToFavorite(movie)
                        item.setIcon(R.drawable.ic_favorite_black_24dp)
                        isFavorite = true
                    }
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun showDetailMovie(movie: Movie) {
        textTitle.text = movie.title
        textReleaseDate.text = movie.releaseDate
        textRating.text = "${movie.voteAverage} / 10"
        textOverview.text = movie.overview
        Glide.with(this)
            .asBitmap()
            .load("$POSTER_URL${movie.posterPath}")
            .into(imagePoster)

        Glide.with(this)
            .asBitmap()
            .load("$BACKDROP_URL${movie.backdropPath}")
            .into(imageBackdrop)

        initView()
        fetchTrailers(movie.id)
    }

    private fun initView() {
        movieDatabase = MovieDatabase.getInstance(this)
        movieDao = movieDatabase.movieDao()

        trailerList = mutableListOf()
        trailerAdapter = TrailerRvAdapter(trailerList)
        rvTrailers.apply {
            layoutManager = LinearLayoutManager(
                this@DetailMovieActivity,
                LinearLayoutManager.HORIZONTAL, false
            )
            setHasFixedSize(true)
            adapter = trailerAdapter
        }
    }

    private fun addToFavorite(movie: Movie) {
        try {
            movieDao.insertMovie(movie)
            Toast.makeText(this, "Add Movie to Favorite", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Log.e("insert Error:", e.localizedMessage!!)
        }
    }

    private fun removeFromFavorite(movieId: Int) {
        try {
            movieDao.deleteMovieById(movieId)
            Toast.makeText(this, "Remove Movie from Favorite", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Log.e("delete Error:", e.localizedMessage!!)
        }
    }

    private fun isMovieFavorite(movieId: Int): Boolean {
        val result = movieDao.findMovieById(movieId)

        Log.d("result", result.toString())

        if (result == movie.id) isFavorite = true

        return isFavorite
    }

    private fun fetchTrailers(movieId: Int) {
        val service = makeService()
        service.getTrailersByMovieId(movieId, "678ef42a1b584848591cbd02ac3899c3").enqueue(object :
            Callback<TrailerResponse> {
            override fun onFailure(call: Call<TrailerResponse>, t: Throwable) {
                Log.i("fetch-trailers-error", t.localizedMessage)
            }

            override fun onResponse(
                call: Call<TrailerResponse>, response: Response<TrailerResponse>
            ) {
                if (response.isSuccessful) {
                    val trailerResponse = response.body()
                    trailerResponse?.results?.map {
                        Log.i("trailer", it.key)
                        trailerList.add(it)
                        trailerAdapter.notifyDataSetChanged()
                    }
                }
            }

        })
    }
}
