package id.co.inaportempat.movielist.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import id.co.inaportempat.movielist.Constants.BACKDROP_URL
import id.co.inaportempat.movielist.Constants.POSTER_URL
import id.co.inaportempat.movielist.R
import id.co.inaportempat.movielist.model.Movie
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

//        val title = intent.getStringExtra("title")
//        val releaseDate = intent.getStringExtra("releaseDate")
//        val popularity = intent.getDoubleExtra("popularity", 0.0)

        val movie = intent.getParcelableExtra<Movie>("movieIntent")
        showDetailMovie(movie)
        supportActionBar?.title = movie?.title
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
    }
}
