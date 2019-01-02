package com.mes.example.kotlinmovieapp.viewmodels

import android.databinding.ObservableField
import com.mes.example.kotlinmovieapp.common.BaseViewModel
import com.mes.example.kotlinmovieapp.models.Movie

class MovieDetailViewModel(private val movieViewModel: MovieViewModel?): BaseViewModel() {

    var title = ObservableField<String>(movieViewModel?.title)
    var date = ObservableField<String>(movieViewModel?.date)
    var duration = ObservableField<String>(movieViewModel?.duration.toString())
    var rate = ObservableField<String>(movieViewModel?.rate.toString())
    var description = ObservableField<String>(movieViewModel?.description)

    fun getMoviePosterImage(): String {
        return movieViewModel?.getMoviePosterImage() ?: ""
    }
}