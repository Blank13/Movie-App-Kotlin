package com.mes.example.kotlinmovieapp.viewmodels

import com.mes.example.kotlinmovieapp.models.Movie
import com.mes.example.kotlinmovieapp.models.Trailer
import java.io.Serializable

class MovieViewModel(private val movie: Movie): Serializable {

    val id = movie.id
    val title = movie.title
    val date = movie.releaseDate
    val rate = movie.voteAverage
    val description = movie.overview
    var duration = movie.duration
    set(value) {
        movie.duration = value
    }

    fun setTrailers(trailers: List<Trailer>) {
//        movie.trailers?.addAll(trailers)
    }

    fun getMovie(): Movie {
        return movie
    }

    fun getMoviePosterImage(): String {
        return "http://image.tmdb.org/t/p/w185/${movie.posterPath}"
    }
}