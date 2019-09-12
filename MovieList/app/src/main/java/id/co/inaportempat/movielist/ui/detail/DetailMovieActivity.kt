package id.co.inaportempat.movielist.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
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
    private lateinit var movie: Movie

    private lateinit var detailViewModel: DetailMovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

//        val title = intent.getStringExtra("title")
//        val releaseDate = intent.getStringExtra("releaseDate")
//        val popularity = intent.getDoubleExtra("popularity", 0.0)

        detailViewModel = ViewModelProviders.of(
            this, DetailMovieViewModelFactory(
                makeService(), MovieDatabase.getInstance(this)
            )
        ).get(DetailMovieViewModel::class.java)

        movie = intent.getParcelableExtra("movieIntent")!!
        showDetailMovie(movie)
        supportActionBar?.title = movie.title

        detailViewModel.isMovieFavorite(movie.id)
        showToast()
        showTrailers()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorite_menu, menu)
        detailViewModel.isFavorited.observe(this, Observer {
            if (it) {
                menu?.findItem(R.id.favoriteButton)?.icon = resources
                    .getDrawable(R.drawable.ic_favorite_black_24dp)
            } else {
                menu?.findItem(R.id.favoriteButton)?.icon = resources
                    .getDrawable(R.drawable.ic_favorite_border_black_24dp)
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favoriteButton -> {
                detailViewModel.onFavoriteButtonClicked(movie)
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
        detailViewModel.fetchTrailers(movie.id)
    }

    private fun initView() {
        rvTrailers.apply {
            layoutManager = LinearLayoutManager(
                this@DetailMovieActivity,
                LinearLayoutManager.HORIZONTAL, false
            )
            setHasFixedSize(true)

        }
    }

    private fun showToast() {
        detailViewModel.toastMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun showTrailers() {
        detailViewModel.trailers.observe(this, Observer {
            trailerAdapter = TrailerRvAdapter(it)
            rvTrailers.adapter = trailerAdapter
        })
    }
}
