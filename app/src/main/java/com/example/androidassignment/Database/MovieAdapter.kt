package com.example.androidassignment.Database


import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidassignment.R

class MovieAdapter(
    private val data: ArrayList<Movie>
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    var onItemClick: ((Movie) -> Unit)? = null

    inner class ViewHolder(item: View) :
        RecyclerView.ViewHolder(item) {

        val movieTitle: TextView = item.findViewById(R.id.movieTitle)
        val movieImage: ImageView = item.findViewById(R.id.movieImage)

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(data[adapterPosition])
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_view_holder, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.movieTitle.text = data[position].movieTitle
        holder.movieImage.setImageResource(data[position].image)
    }

    override fun getItemCount(): Int {
        return data.size
    }

}

