package com.example.myapplication

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R.*
import com.example.myapplication.databinding.FragmentSecondBinding
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding

    private lateinit var viewModel: MoviesListViewModel
    private lateinit var movieAdapter : MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater)
        movieAdapter = MovieAdapter()
        val favoriteText = "Movie has been added to favorites!"
        val unFavoriteText = "Movie has been removed from favorites!"
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(requireContext(), favoriteText, duration)
        movieAdapter.onItemClick = {movie ->
            lifecycleScope.launch {
                updateLikedMovies(movie)
            }
            toast.show()
        }
        binding.moviesList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = movieAdapter
        }
        viewModel = ViewModelProvider(this)[MoviesListViewModel::class.java]
        viewModel.getMostPopularMovies()
        viewModel.observeMovieLiveData().observe(viewLifecycleOwner) { movieList ->
            movieAdapter.setMovieList(movieList)
        }
        return binding.root

    }
    fun updateState(){
        
    }
    private suspend fun updateLikedMovies(movie: MovieDataClass){
        viewModel.addLikedMovieToDatabase(movie)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fab.setOnClickListener {
            //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            LikedMoviesDialogFragment(viewModel.likedMovies).show(
                childFragmentManager, "Tag")
        }
    }
}