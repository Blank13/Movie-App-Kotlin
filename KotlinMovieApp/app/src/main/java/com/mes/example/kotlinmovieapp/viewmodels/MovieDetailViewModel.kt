package com.mes.example.kotlinmovieapp.viewmodels

import android.databinding.ObservableField
import android.util.Log
import com.mes.example.kotlinmovieapp.data.MoviesRepository
import com.mes.example.kotlinmovieapp.utils.LOGGER_TAG
import java.io.Serializable

class MovieDetailViewModel(private val movieViewModel: MovieViewModel?): Serializable {

    var title = ObservableField<String>(movieViewModel?.title)
    var date = ObservableField<String>(movieViewModel?.date)
    var duration = ObservableField<String>("0 min")
    var rate = ObservableField<String>("${movieViewModel?.rate ?: 0} / 10")
    var description = ObservableField<String>(movieViewModel?.description)

    fun getMoviePosterImage(): String {
        return movieViewModel?.getMoviePosterImage() ?: ""
    }

    fun getMovieDuration() {
        MoviesRepository().getDurationOfMovie(movieViewModel?.id ?: 0,
            { duration -> this.duration.set("$duration min") },
            { error -> Log.d(LOGGER_TAG, error) })
    }
}