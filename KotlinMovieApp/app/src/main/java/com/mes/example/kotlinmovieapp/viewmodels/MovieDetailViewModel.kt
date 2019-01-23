package com.mes.example.kotlinmovieapp.viewmodels

import android.annotation.SuppressLint
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.util.Log
import com.mes.example.kotlinmovieapp.data.MoviesRepository
import com.mes.example.kotlinmovieapp.data.TrailersRepository
import com.mes.example.kotlinmovieapp.models.Movie
import com.mes.example.kotlinmovieapp.utils.LOGGER_TAG
import io.reactivex.Observable.fromArray
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.RealmList
import java.io.Serializable

@SuppressLint("CheckResult")
class MovieDetailViewModel(private var movie: Movie): Serializable {

    var title = ObservableField<String>(movie.title)
    var date = ObservableField<String>(movie.releaseDate)
    var duration = ObservableField<String>("${movie.duration} min")
    var rate = ObservableField<String>("${movie.voteAverage} / 10")
    var description = ObservableField<String>(movie.overview)
    var isFavourite = ObservableBoolean(false)
    var isTrailersLoading: ObservableBoolean = ObservableBoolean(true)
    var trailersViewModels: ObservableArrayList<TrailerViewModel> = ObservableArrayList()

    private val moviesRepository: MoviesRepository = MoviesRepository()
    private val trailersRepository: TrailersRepository = TrailersRepository()

    init {
        isFavourite.set(MoviesRepository().checkIfMovieInFavourite(movie.id))
        getMovieIfFavourite()
    }

    fun getMovieIfFavourite(){
        if (isFavourite.get()) {
            movie = MoviesRepository().getMovieWithId(movie.id) ?: Movie()
            duration.set("${movie.duration} min")
            movie.trailers?.toList()?.let { trailers ->
                fromArray(trailers).flatMapIterable { trailers }
                    .map { TrailerViewModel(it) }
                    .toList()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { trailersViewModelsList ->
                        trailersViewModels.clear()
                        trailersViewModels.addAll(trailersViewModelsList)
                        if (movie.trailers == null) {
                            movie.trailers = RealmList()
                        }
                        movie.trailers?.clear()
                        movie.trailers?.addAll(trailers)
                        isTrailersLoading.set(false)
                    }
            }
        }
    }

    fun getMoviePosterImage(): String {
        return "http://image.tmdb.org/t/p/w185/${movie.posterPath}"
    }

    fun updateMovieDuration() {
        moviesRepository.getDurationOfMovie(movie.id,
            { duration ->
                this.duration.set("$duration min")
                movie.duration = duration
                if (isFavourite.get()) {
                    saveToFavourite()
                }
            }, { error -> Log.d(LOGGER_TAG, error) })
    }

    fun updateTrailers() {
        trailersRepository.getTrailerForMovie(movie.id,
            { trailers ->
                fromArray(trailers).flatMapIterable { trailers }
                    .map { TrailerViewModel(it) }
                    .toList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { trailersViewModelsList ->
                        trailersViewModels.clear()
                        trailersViewModels.addAll(trailersViewModelsList)
                        if (movie.trailers == null) {
                            movie.trailers = RealmList()
                        }
                        movie.trailers?.clear()
                        movie.trailers?.addAll(trailers)
                        isTrailersLoading.set(false)
                        if (isFavourite.get()) {
                            saveToFavourite()
                        }
                    }
            },{ error -> Log.d(LOGGER_TAG, error) })
    }

    fun favouriteButtonClicked() {
        if (isFavourite.get()) {
            deleteFromFavourite()
            isFavourite.set(false)
        }
        else {
            saveToFavourite()
            isFavourite.set(true)
        }
    }

    fun saveToFavourite() {
        moviesRepository.saveMovie(movie)
    }

    fun deleteFromFavourite(){
        moviesRepository.deleteMovie(movie.id)
    }
}