package com.mes.example.kotlinmovieapp.view.moviesposters

import android.databinding.DataBindingUtil
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mes.example.kotlinmovieapp.R
import com.mes.example.kotlinmovieapp.common.LoadingViewHolder
import com.mes.example.kotlinmovieapp.databinding.ItemMoviePosterBinding
import com.mes.example.kotlinmovieapp.viewmodels.MovieViewModel

class MoviesPostersRecyclerViewAdapter: RecyclerView.Adapter<ViewHolder>() {

    enum class PostersViewHolderTypes(val value: Int) {
        Poster(0),
        Loader(1)
    }

    var moviesViewModels: ObservableArrayList<MovieViewModel> = ObservableArrayList()
    var postersFragment: MoviesPostersFragment? = null
    var isLoaderNeeded: ObservableBoolean = ObservableBoolean(true)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        when (viewType) {
            PostersViewHolderTypes.Loader.value -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false)
                return LoadingViewHolder(view)
            }
            PostersViewHolderTypes.Poster.value -> {
                val itemBinding = DataBindingUtil.inflate<ItemMoviePosterBinding>(LayoutInflater.from(parent.context),
                    R.layout.item_movie_poster, parent, false)
                return MoviePosterViewHolder(itemBinding.root)
            }
        }
        return object: ViewHolder(View(parent.context)){}
    }

    override fun getItemCount(): Int {
        return moviesViewModels.size + if (isLoaderNeeded.get()) 1 else 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == moviesViewModels.size) PostersViewHolderTypes.Loader.value
        else PostersViewHolderTypes.Poster.value
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        if  (getItemViewType(position) == MoviesPostersRecyclerViewAdapter.PostersViewHolderTypes.Poster.value) {
            (viewHolder as? MoviePosterViewHolder)?.let { moviePosterViewHolder ->
                moviePosterViewHolder.binding?.movieViewModel = moviesViewModels[position]
                moviePosterViewHolder.binding?.root?.setOnClickListener { postersFragment?.onPosterSelected(position) }
            }
        }
    }

}