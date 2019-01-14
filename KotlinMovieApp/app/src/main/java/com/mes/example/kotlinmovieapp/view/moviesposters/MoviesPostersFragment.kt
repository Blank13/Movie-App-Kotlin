package com.mes.example.kotlinmovieapp.view.moviesposters

import android.annotation.SuppressLint
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.jakewharton.rxbinding2.support.v7.widget.scrollStateChanges
import com.mes.example.kotlinmovieapp.R
import com.mes.example.kotlinmovieapp.databinding.FragmentMoviesPostersBinding
import com.mes.example.kotlinmovieapp.delegates.MoviesPostersFragmentDelegate
import com.mes.example.kotlinmovieapp.view.moviedetail.MovieDetailActivity
import com.mes.example.kotlinmovieapp.viewmodels.MoviesPostersViewModel


class MoviesPostersFragment: Fragment(), MoviesPostersFragmentDelegate {

    private lateinit var binding: FragmentMoviesPostersBinding
    private var moviesPostersViewModel: MoviesPostersViewModel = MoviesPostersViewModel()
    private val delegate = object : MoviesPostersFragmentDelegate by this {}

    @SuppressLint("CheckResult")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        moviesPostersViewModel.moviesPostersFragmentDelegate = delegate
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies_posters, container, false)
        binding.moviesRecyclerview.layoutManager = GridLayoutManager(context, 2)
        binding.postersView = this
        binding.moviesViewModel = moviesPostersViewModel
        moviesPostersViewModel.updateMovies()

        binding.moviesRecyclerview.scrollStateChanges().subscribe {
//            Toast.makeText(context,"$it",Toast.LENGTH_SHORT).show()
            if (!binding.moviesRecyclerview.canScrollVertically(1)) {
                if (!moviesPostersViewModel.isLoading.get()){
                    moviesPostersViewModel.updateMovies()
                }
            }
        }
        return binding.root
    }

    fun onPosterSelected(position: Int) {
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra("MovieViewModel", moviesPostersViewModel.getMovieViewModelForPosterAt(position))
        startActivity(intent)
    }

    override fun onGetingMoviesError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

}