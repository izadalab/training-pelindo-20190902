package id.co.inaportempat.movielist.data.remote

import id.co.inaportempat.movielist.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface MovieApiEndpoint {
    @GET("movie/now_playing")
    fun getNowPlayingMovies() : Call<MovieResponse>

    @GET("movie/popular")
    fun getPopularMovies() : Call<MovieResponse>
}