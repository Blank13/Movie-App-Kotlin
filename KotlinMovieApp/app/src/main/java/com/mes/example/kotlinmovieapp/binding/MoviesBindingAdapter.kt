package com.mes.example.kotlinmovieapp.binding

import android.databinding.BindingAdapter
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.support.v7.widget.RecyclerView
import com.mes.example.kotlinmovieapp.view.moviesposters.MoviesPostersFragment
import com.mes.example.kotlinmovieapp.view.moviesposters.MoviesPostersRecyclerViewAdapter
import com.mes.example.kotlinmovieapp.viewmodels.MovieViewModel

@BindingAdapter(value = ["movieslist", "listener", "loader"])
fun setMoviesItems(recyclerView: RecyclerView, moviesViewModels: ObservableArrayList<MovieViewModel>,
             fragment: MoviesPostersFragment, isLoaderNeeded: ObservableBoolean) {
    var moviesAdapter: MoviesPostersRecyclerViewAdapter? = recyclerView.adapter as? MoviesPostersRecyclerViewAdapter
    if (moviesAdapter == null) {
        moviesAdapter = MoviesPostersRecyclerViewAdapter()
        recyclerView.adapter = moviesAdapter
        moviesAdapter.moviesViewModels = moviesViewModels
        moviesAdapter.postersFragment = fragment
        moviesAdapter.isLoaderNeeded = isLoaderNeeded
    }
    moviesAdapter.notifyDataSetChanged()
}
