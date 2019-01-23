package com.mes.example.kotlinmovieapp.viewmodels

import com.mes.example.kotlinmovieapp.models.Movie
import java.io.Serializable

class MovieViewModel(private var movie: Movie): Serializable {

    fun getMoviePosterImage(): String {
        return "http://image.tmdb.org/t/p/w185/${movie.posterPath}"
    }

    fun getMovie(): Movie {
        return movie
    }
}