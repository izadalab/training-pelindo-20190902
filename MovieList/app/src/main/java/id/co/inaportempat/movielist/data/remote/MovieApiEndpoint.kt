package id.co.inaportempat.movielist.data.remote

import id.co.inaportempat.movielist.model.MovieResponse
import id.co.inaportempat.movielist.model.TrailerResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiEndpoint {
    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("api_key") apiKey: String
    ): Call<MovieResponse>

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String
    ): Call<MovieResponse>

    @GET("movie/{movie_id}/videos")
    fun getTrailersByMovieId(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Call<TrailerResponse>
}