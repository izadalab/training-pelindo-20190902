package id.co.inaportempat.movielist.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object MovieService {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    fun makeService() : MovieApiEndpoint {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(MovieApiEndpoint::class.java)
    }
}
