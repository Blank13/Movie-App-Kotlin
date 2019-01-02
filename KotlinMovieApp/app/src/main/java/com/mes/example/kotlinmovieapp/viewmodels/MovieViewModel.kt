package com.mes.example.kotlinmovieapp.viewmodels

import com.mes.example.kotlinmovieapp.common.BaseViewModel
import com.mes.example.kotlinmovieapp.models.Movie
import java.io.Serializable

class MovieViewModel(private val movie: Movie): BaseViewModel(), Serializable {

    var title = movie.title
    var date = movie.releaseDate
    var duration = 0
    var rate = movie.voteAverage
    var description = movie.overview

    fun getMoviePosterImage(): String {
        return movie.posterPath
    }
}