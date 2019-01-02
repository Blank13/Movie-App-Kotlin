package com.mes.example.kotlinmovieapp.binding

import android.databinding.BindingAdapter
import android.databinding.ObservableArrayList
import android.support.v7.widget.RecyclerView
import com.mes.example.kotlinmovieapp.view.moviesposters.MoviesPostersFragment
import com.mes.example.kotlinmovieapp.view.moviesposters.MoviesPostersRecyclerViewAdapter
import com.mes.example.kotlinmovieapp.viewmodels.MovieViewModel

@BindingAdapter(value = ["movieslist", "listener"])
fun setItems(recyclerView: RecyclerView, moviesViewModels: ObservableArrayList<MovieViewModel>,
             fragment: MoviesPostersFragment) {
    var moviesAdapter: MoviesPostersRecyclerViewAdapter? = recyclerView.adapter as MoviesPostersRecyclerViewAdapter
    if (moviesAdapter == null) {
        moviesAdapter = MoviesPostersRecyclerViewAdapter()
        recyclerView.adapter = moviesAdapter
    }
    moviesAdapter.postersFragment = fragment
    moviesAdapter.moviesViewModels = moviesViewModels
    moviesAdapter.notifyDataSetChanged()
}
