package com.mes.example.kotlinmovieapp.viewmodels

import android.databinding.BaseObservable
import com.mes.example.kotlinmovieapp.common.BaseViewModel
import com.mes.example.kotlinmovieapp.models.Movie

class MovieViewModel(private val movie: Movie): BaseViewModel() {

    fun getMoviePosterImage(): String {
        return movie.posterPath
    }
}