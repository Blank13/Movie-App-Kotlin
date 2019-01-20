package com.mes.example.kotlinmovieapp.viewmodels

import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.util.Log
import com.mes.example.kotlinmovieapp.data.MoviesRepository
import com.mes.example.kotlinmovieapp.data.TrailersRepository
import com.mes.example.kotlinmovieapp.utils.LOGGER_TAG
import io.reactivex.Observable.fromArray
import java.io.Serializable

class MovieDetailViewModel(private val movieViewModel: MovieViewModel?): Serializable {

    var title = ObservableField<String>(movieViewModel?.title)
    var date = ObservableField<String>(movieViewModel?.date)
    var duration = ObservableField<String>("${movieViewModel?.duration ?: 0} min")
    var rate = ObservableField<String>("${movieViewModel?.rate ?: 0} / 10")
    var description = ObservableField<String>(movieViewModel?.description)

    var isTrailersLoading: ObservableBoolean = ObservableBoolean(true)
    var trailersViewModels: ObservableArrayList<TrailerViewModel> = ObservableArrayList()

    fun getMoviePosterImage(): String {
        return movieViewModel?.getMoviePosterImage() ?: ""
    }

    fun getMovieDuration() {
        MoviesRepository().getDurationOfMovie(movieViewModel?.id ?: 0,
            { duration ->
                this.duration.set("$duration min")
                movieViewModel?.duration = duration },
            { error -> Log.d(LOGGER_TAG, error) })
    }

    fun getTrailers() {
        TrailersRepository().getTrailerForMovie(movieViewModel?.id ?:0,
            { trailers ->
                fromArray(trailers).flatMapIterable { trailers }
                    .map { TrailerViewModel(it) }
                    .toList()
                    .subscribe { trailersViewModelsList ->
                        trailersViewModels.clear()
                        trailersViewModels.addAll(trailersViewModelsList)
                        movieViewModel?.setTrailers(trailers)
                        isTrailersLoading.set(false)
                    }
            },{ error -> Log.d(LOGGER_TAG, error) })
    }

    fun saveToFavourite() {
        movieViewModel?.let {
            MoviesRepository().saveMovieToDB(it.getMovie())
        }
    }
}