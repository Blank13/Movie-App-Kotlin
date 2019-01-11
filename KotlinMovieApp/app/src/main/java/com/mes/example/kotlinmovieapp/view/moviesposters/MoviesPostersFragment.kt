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
import android.widget.Toast
import com.jakewharton.rxbinding2.support.v7.widget.scrollEvents
import com.mes.example.kotlinmovieapp.R
import com.mes.example.kotlinmovieapp.databinding.FragmentMoviesPostersBinding
import com.mes.example.kotlinmovieapp.utils.LOGGER_TAG
import com.mes.example.kotlinmovieapp.view.moviedetail.MovieDetailActivity
import com.mes.example.kotlinmovieapp.viewmodels.MoviesPostersViewModel

class MoviesPostersFragment: Fragment() {

    private lateinit var binding: FragmentMoviesPostersBinding
    private var moviesPostersViewModel: MoviesPostersViewModel = MoviesPostersViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (savedInstanceState != null) {
            Log.d(LOGGER_TAG,"HAHAHA It wasn't empty : " + savedInstanceState.toString())
        }
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies_posters, container, false)
        binding.moviesRecyclerview.adapter = MoviesPostersRecyclerViewAdapter()
        binding.moviesRecyclerview.layoutManager = GridLayoutManager(context, 2)
        binding.postersView = this
        binding.moviesViewModel = moviesPostersViewModel
        moviesPostersViewModel.updateMovies()

        binding.moviesRecyclerview.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                recyclerView.layoutManager?.let {
                    val totalHeight = it.height
                    val itemHeight = totalHeight / (it.itemCount / 2)
                    if (dy > totalHeight - (itemHeight * 2)) {
                        Toast.makeText(context,"Reach Before End from Scrolling", Toast.LENGTH_LONG).show()
                    }
                }

            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    Toast.makeText(context,"Reach End from StateChanged", Toast.LENGTH_LONG).show()
                }
            }
        })

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("position", 3)
        super.onSaveInstanceState(outState)
        outState.putInt("position", 2)
    }

    fun onPosterSelected(position: Int) {
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra("MovieViewModel", moviesPostersViewModel.getMovieViewModelForPosterAt(position))
        startActivity(intent)
    }


}