package com.example.myapplication

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("poster_path")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        Glide.with(imgView)
            .load("https://image.tmdb.org/t/p/w500$imgUrl")
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.ic_broken_image)
            .into(imgView)
        }
}

//@BindingAdapter("listData")
//fun bindRecyclerView(recyclerView: RecyclerView, movies: List<MovieDataClass>?) {
//    val adapter = recyclerView.adapter as MoviesListAdapter
//    adapter.moviesList = movies
//}