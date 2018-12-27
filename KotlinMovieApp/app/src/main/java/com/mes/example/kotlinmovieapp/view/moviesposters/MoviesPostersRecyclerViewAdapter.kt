package com.mes.example.kotlinmovieapp.view.moviesposters

import android.databinding.DataBindingUtil
import android.databinding.ObservableArrayList
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mes.example.kotlinmovieapp.BR
import com.mes.example.kotlinmovieapp.R
import com.mes.example.kotlinmovieapp.databinding.ItemMoviePosterBinding
import com.mes.example.kotlinmovieapp.viewmodels.MovieViewModel

class MoviesPostersRecyclerViewAdapter: RecyclerView.Adapter<MoviePosterViewHolder>() {

    var moviesViewModels: ObservableArrayList<MovieViewModel> = ObservableArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviePosterViewHolder {
        val itemBinding = DataBindingUtil.inflate<ItemMoviePosterBinding>(LayoutInflater.from(parent.context),
            R.layout.item_movie_poster, parent, false)
        return MoviePosterViewHolder(itemBinding.root)
    }

    override fun getItemCount(): Int {
        return moviesViewModels.size
    }

    override fun onBindViewHolder(viewHolder: MoviePosterViewHolder, position: Int) {
        viewHolder.binding?.setVariable(BR.movieViewModel, moviesViewModels[position])
    }

}