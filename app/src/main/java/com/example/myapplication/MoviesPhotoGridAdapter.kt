package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.MoviesItemBinding

class MoviesViewHolder(var binding: MoviesItemBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(movie: MovieDataClass) {
        binding.property = movie
        binding.executePendingBindings()
    }

}

class PhotoGridAdapter :ListAdapter<MovieDataClass, MoviesViewHolder> (DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(MoviesItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = getItem(position)
//        Glide.with(holder.itemView)
//            .load("https://api.themoviedb.org/3/movie/"+movie.posterImage)
//            .into(holder.binding.movieImage)
//        holder.binding.moviesItem.text = movie.title
        holder.bind(movie)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<MovieDataClass>() {
        override fun areItemsTheSame(oldItem: MovieDataClass, newItem: MovieDataClass): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MovieDataClass, newItem: MovieDataClass): Boolean {
            return oldItem.id == newItem.id
        }

    }
}