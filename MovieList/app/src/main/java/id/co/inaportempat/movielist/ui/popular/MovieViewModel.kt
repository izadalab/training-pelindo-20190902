package id.co.inaportempat.movielist.ui.popular

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.co.inaportempat.movielist.data.remote.MovieApiEndpoint
import id.co.inaportempat.movielist.model.Movie
import id.co.inaportempat.movielist.model.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel(private val api: MovieApiEndpoint) : ViewModel() {

    val moviesLiveData: MutableLiveData<List<Movie>> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun fetchPopularMovies() {
        isLoading.postValue(true)
        api.getPopularMovies("678ef42a1b584848591cbd02ac3899c3")
            .enqueue(object : Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.e("Error Message", t.localizedMessage!!)
                    isLoading.postValue(false)
                }

                override fun onResponse(
                    call: Call<MovieResponse>, response: Response<MovieResponse>
                ) {
                    if (response.isSuccessful) {
                        val movieResponse = response.body()
                        moviesLiveData.postValue(movieResponse?.results)
                    } else {
                        Log.e("movies", "get response failed")
                    }
                    try {
                        isLoading.postValue(false)
                    } catch (e: IllegalStateException) {
                        e.printStackTrace()
                        Log.e("state", e.localizedMessage!!)
                    }

                }
            })
    }

}