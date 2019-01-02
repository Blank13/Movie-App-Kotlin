package com.mes.example.kotlinmovieapp.view.moviedetail

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mes.example.kotlinmovieapp.R
import com.mes.example.kotlinmovieapp.databinding.FragmentMovieDetailBinding
import com.mes.example.kotlinmovieapp.utils.LOGGER_TAG
import com.mes.example.kotlinmovieapp.viewmodels.MovieDetailViewModel

class MovieDetailFragment: Fragment() {

    private var binding: FragmentMovieDetailBinding? = null
    private var movieDetailViewModel: MovieDetailViewModel = MovieDetailViewModel(null)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail,container, false)
        if (binding != null) {
            if (savedInstanceState != null && savedInstanceState.containsKey("MovieViewModel")) {
                Log.d(LOGGER_TAG, "There was data: " + savedInstanceState["MovieViewModel"])
            }
        }
        return binding?.root
    }
}