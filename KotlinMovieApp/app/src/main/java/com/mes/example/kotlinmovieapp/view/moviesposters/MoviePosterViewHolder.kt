package com.mes.example.kotlinmovieapp.view.moviesposters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import com.mes.example.kotlinmovieapp.databinding.ItemMoviePosterBinding

class MoviePosterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var binding: ItemMoviePosterBinding?

    init {
        binding = DataBindingUtil.bind(view)
    }
}