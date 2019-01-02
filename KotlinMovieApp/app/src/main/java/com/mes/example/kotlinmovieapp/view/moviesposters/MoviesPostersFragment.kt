package com.mes.example.kotlinmovieapp.view.moviesposters

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mes.example.kotlinmovieapp.R
import com.mes.example.kotlinmovieapp.data.MoviesRepository
import com.mes.example.kotlinmovieapp.databinding.FragmentMoviesPostersBinding
import com.mes.example.kotlinmovieapp.models.Movie
import com.mes.example.kotlinmovieapp.utils.SortTypes
import com.mes.example.kotlinmovieapp.view.moviedetail.MovieDetailActivity
import com.mes.example.kotlinmovieapp.view.moviedetail.MovieDetailFragment
import com.mes.example.kotlinmovieapp.viewmodels.MovieViewModel
import com.mes.example.kotlinmovieapp.viewmodels.MoviesPostersViewModel

class MoviesPostersFragment: Fragment() {

    private var binding: FragmentMoviesPostersBinding? = null
    private var moviesPostersViewModel: MoviesPostersViewModel = MoviesPostersViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies_posters, container, false)
        if (binding != null) {
            binding!!.moviesRecyclerview.adapter = MoviesPostersRecyclerViewAdapter()
            binding!!.moviesRecyclerview.layoutManager = GridLayoutManager(context, 2)
            binding?.postersView = this
            binding?.moviesViewModel = moviesPostersViewModel
            lifecycle.addObserver(moviesPostersViewModel)
            binding?.setLifecycleOwner(this)
        }
        moviesPostersViewModel.updateMovies()
        return binding?.root
    }

    fun onPosterSelected(position: Int) {
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra("MovieViewModel", moviesPostersViewModel.getMovieViewModelForPosterAt(position))
        startActivity(intent)
    }

}