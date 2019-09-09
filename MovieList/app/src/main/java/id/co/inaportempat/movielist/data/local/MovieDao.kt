package id.co.inaportempat.movielist.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import id.co.inaportempat.movielist.model.Movie

@Dao
interface MovieDao {
    @Insert
    fun insertMovie(movie: Movie)

    @Query("SELECT * FROM movies")
    fun getMovies(): List<Movie>

    @Query("SELECT * FROM movies WHERE id = :id")
    fun findMovieById(id: Int): Int

    @Query("DELETE FROM movies WHERE id = :id")
    fun deleteMovieById(id: Int)
}