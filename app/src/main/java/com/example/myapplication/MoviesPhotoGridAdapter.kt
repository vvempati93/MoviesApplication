package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.MoviesItemBinding
import kotlinx.coroutines.NonDisposableHandle.parent

const val IMAGE_URL_PREFIX = "https://image.tmdb.org/t/p/w500"
class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    private var movieList = ArrayList<MovieDataClass>()
    var onItemClick: ((MovieDataClass) -> Unit)? = null
    fun setMovieList(movieList : List<MovieDataClass>){
        this.movieList = movieList as ArrayList<MovieDataClass>
        notifyDataSetChanged()
    }
    inner class ViewHolder(val binding : MoviesItemBinding) : RecyclerView.ViewHolder(binding.root){
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(
                    movieList[adapterPosition]
                )
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MoviesItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(IMAGE_URL_PREFIX+movieList[position].posterImage)
            .into(holder.binding.movieImage)
        holder.binding.moviesItem.text = movieList[position].title
    }
    override fun getItemCount(): Int {
        return movieList.size
    }}