package com.mes.example.kotlinmovieapp.viewmodels

import android.databinding.ObservableArrayList
import android.util.Log
import com.mes.example.kotlinmovieapp.common.BaseViewModel
import com.mes.example.kotlinmovieapp.data.MoviesRepository
import com.mes.example.kotlinmovieapp.utils.LOGGER_TAG
import com.mes.example.kotlinmovieapp.utils.SortTypes
import io.reactivex.Observable.fromArray

class MoviesPostersViewModel: BaseViewModel() {

    var moviesViewModels: ObservableArrayList<MovieViewModel> = ObservableArrayList()

    override fun onCreate() {
        super.onCreate()
        updateMovies()
    }

    fun updateMovies() {
        MoviesRepository().getMovies(SortTypes.PopularDec, 1) { movies, error ->
            if (movies != null && error == null) {
                fromArray(movies).flatMapIterable { movies }
                    .map { MovieViewModel(it) }
                    .toList()
                    .subscribe { moviesList -> moviesViewModels.addAll(moviesList) }
            }
            else {
                Log.e(LOGGER_TAG, error)
            }
        }
    }

    fun getMovieViewModelForPosterAt(position: Int) : MovieViewModel {
        return moviesViewModels[position]
    }
}