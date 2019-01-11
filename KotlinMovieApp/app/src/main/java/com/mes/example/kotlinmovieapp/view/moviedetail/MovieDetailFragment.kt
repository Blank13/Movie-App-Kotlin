package com.mes.example.kotlinmovieapp.view.moviedetail

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mes.example.kotlinmovieapp.R
import com.mes.example.kotlinmovieapp.databinding.FragmentMovieDetailBinding
import com.mes.example.kotlinmovieapp.viewmodels.MovieDetailViewModel
import com.mes.example.kotlinmovieapp.viewmodels.MovieViewModel

class MovieDetailFragment: Fragment() {

    private lateinit var binding: FragmentMovieDetailBinding
    var movieDetailViewModel: MovieDetailViewModel = MovieDetailViewModel(null)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail,container, false)
        val intent = activity?.intent
        intent?.extras?.let {
            val movieViewModel = it["MovieViewModel"] as MovieViewModel?
            movieDetailViewModel = MovieDetailViewModel(movieViewModel)
        }
        binding.movieDetailViewModel = movieDetailViewModel
        movieDetailViewModel.getMovieDuration()
        return binding.root
    }
}