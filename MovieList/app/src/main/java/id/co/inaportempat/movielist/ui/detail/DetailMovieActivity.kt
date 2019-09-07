package id.co.inaportempat.movielist.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import id.co.inaportempat.movielist.R
import id.co.inaportempat.movielist.model.Movie

class DetailMovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

//        val title = intent.getStringExtra("title")
//        val releaseDate = intent.getStringExtra("releaseDate")
//        val popularity = intent.getDoubleExtra("popularity", 0.0)

        val movie = intent.getParcelableExtra<Movie>("movieIntent")


        Log.i("Detail Movie", "Movie title : ${movie?.title}, ${movie?.releaseDate}, ${movie?.voteCount}")

        supportActionBar?.title = movie?.title
    }
}
