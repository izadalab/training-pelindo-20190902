package id.co.inaportempat.movielist.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.co.inaportempat.movielist.R
import id.co.inaportempat.movielist.model.Trailer
import kotlinx.android.synthetic.main.list_trailer_item.view.*

class TrailerRvAdapter(
    private val trailers: List<Trailer>
) : RecyclerView.Adapter<TrailerRvAdapter.TrailerViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): TrailerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_trailer_item, parent, false)
        return TrailerViewHolder(view)
    }

    override fun getItemCount(): Int = trailers.size

    override fun onBindViewHolder(holder: TrailerViewHolder, position: Int) {
        val trailer = trailers[position]
        holder.bindView(trailer)
    }

    inner class TrailerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(trailer: Trailer) {
            itemView.apply {
                Glide.with(context).asBitmap()
                    .load("https://i1.ytimg.com/vi/" + trailer.key + "/0.jpg")
                    .into(imageThumbnail)
            }
        }

    }
}