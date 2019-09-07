package id.co.inaportempat.movielist.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import id.co.inaportempat.movielist.R
import id.co.inaportempat.movielist.model.Movie
import kotlinx.android.synthetic.main.list_movie_item.view.*

class MovieRvAdapter(
    private val movies: List<Movie>
) : RecyclerView.Adapter<MovieRvAdapter.MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.list_movie_item, parent, false
        )
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
//        holder.itemView.imagePoster.setBackgroundResource(movie.image)
//        holder.itemView.textTitle.text = movie.title

        holder.bindView(movie)

    }

    companion object {
        const val POSTER_URL = "https://image.tmdb.org/t/p/w185/"
    }

    // cara 1
    inner class MovieViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bindView(movie: Movie) {
            itemView.apply {
                textTitle.text = movie.title
                Glide
                    .with(context).asDrawable()
                    .load(POSTER_URL + movie.posterPath)
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imagePoster)
            }
        }
    }

}

// cara 2
/*
class MovieViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

}*/
