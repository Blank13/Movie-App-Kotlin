package com.mes.example.kotlinmovieapp.viewmodels

import android.databinding.ObservableField
import com.mes.example.kotlinmovieapp.common.BaseViewModel
import com.mes.example.kotlinmovieapp.data.MoviesRepository

class MovieDetailViewModel(private val movieViewModel: MovieViewModel?): BaseViewModel() {

    var title = ObservableField<String>(movieViewModel?.title)
    var date = ObservableField<String>(movieViewModel?.date)
    var duration = ObservableField<String>("0 min")
    var rate = ObservableField<String>("${movieViewModel?.rate ?: 0} / 10")
    var description = ObservableField<String>(movieViewModel?.description)

    fun getMoviePosterImage(): String {
        return movieViewModel?.getMoviePosterImage() ?: ""
    }

    fun getMovieDuration() {
        MoviesRepository().getDurationOfMovie(movieViewModel?.id ?: 0) { duration ->
            this.duration.set("$duration min")
        }
    }
}