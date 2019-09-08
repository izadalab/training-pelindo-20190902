package id.co.inaportempat.movielist.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import id.co.inaportempat.movielist.Constants.BACKDROP_URL
import id.co.inaportempat.movielist.Constants.POSTER_URL
import id.co.inaportempat.movielist.R
import id.co.inaportempat.movielist.data.remote.MovieService.makeService
import id.co.inaportempat.movielist.model.Movie
import id.co.inaportempat.movielist.model.TrailerResponse
import kotlinx.android.synthetic.main.activity_detail_movie.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        fetchTrailers(movie.id)
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
                    }
                }
            }

        })
    }
}
