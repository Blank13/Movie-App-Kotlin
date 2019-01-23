package com.mes.example.kotlinmovieapp.view.moviedetail

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.mes.example.kotlinmovieapp.R
import com.mes.example.kotlinmovieapp.databinding.FragmentMovieDetailBinding
import com.mes.example.kotlinmovieapp.models.Movie
import com.mes.example.kotlinmovieapp.viewmodels.MovieDetailViewModel

class MovieDetailFragment: Fragment() {

    private lateinit var binding: FragmentMovieDetailBinding
    private var movieDetailViewModel: MovieDetailViewModel = MovieDetailViewModel(Movie())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail,container, false)
        activity?.intent?.extras?.getString("MovieDetailViewModel")?.let { movieViewModelJson ->
            movieDetailViewModel = Gson().fromJson(movieViewModelJson, MovieDetailViewModel::class.java)
        }
        binding.trailersRecyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        binding.movieDetailViewModel = movieDetailViewModel
//        movieDetailViewModel.getMovieIfFavourite()
        movieDetailViewModel.updateMovieDuration()
        movieDetailViewModel.updateTrailers()
        return binding.root
    }
}