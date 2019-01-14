package com.mes.example.kotlinmovieapp.view.moviesposters

import android.databinding.DataBindingUtil
import android.databinding.ObservableArrayList
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mes.example.kotlinmovieapp.R
import com.mes.example.kotlinmovieapp.databinding.ItemMoviePosterBinding
import com.mes.example.kotlinmovieapp.viewmodels.MovieViewModel

class MoviesPostersRecyclerViewAdapter: RecyclerView.Adapter<MoviePosterViewHolder>() {

    private enum class ViewHolderTypes(val value: Int) {
        Poster(0),
        Loader(1)
    }

    var moviesViewModels: ObservableArrayList<MovieViewModel> = ObservableArrayList()
    var postersFragment: MoviesPostersFragment? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviePosterViewHolder {
//        when (viewType) {
//            ViewHolderTypes.Poster.value -> {
//                val itemBinding = DataBindingUtil.inflate<ItemMoviePosterBinding>(LayoutInflater.from(parent.context),
//                    R.layout.item_movie_poster, parent, false)
//            }
//            ViewHolderTypes.Loader.value -> {
//
//            }
//        }
        val itemBinding = DataBindingUtil.inflate<ItemMoviePosterBinding>(LayoutInflater.from(parent.context),
            R.layout.item_movie_poster, parent, false)
        return MoviePosterViewHolder(itemBinding.root)
    }

    override fun getItemCount(): Int {
        return moviesViewModels.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == moviesViewModels.size - 1) ViewHolderTypes.Loader.value else ViewHolderTypes.Poster.value
    }

    override fun onBindViewHolder(viewHolder: MoviePosterViewHolder, position: Int) {
        viewHolder.binding?.movieViewModel = moviesViewModels[position]
        viewHolder.binding?.root?.setOnClickListener { postersFragment?.onPosterSelected(position) }
    }

}