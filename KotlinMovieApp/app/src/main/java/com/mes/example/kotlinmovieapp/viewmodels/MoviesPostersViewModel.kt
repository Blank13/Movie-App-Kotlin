package com.mes.example.kotlinmovieapp.viewmodels

import android.databinding.Bindable
import android.databinding.Observable
import android.databinding.ObservableArrayList
import android.util.Log
import com.mes.example.kotlinmovieapp.BR
import com.mes.example.kotlinmovieapp.common.BaseViewModel
import com.mes.example.kotlinmovieapp.data.MoviesRepository
import com.mes.example.kotlinmovieapp.utils.LOGGER_TAG
import com.mes.example.kotlinmovieapp.utils.SortTypes
import io.reactivex.Observable.fromArray

class MoviesPostersViewModel: BaseViewModel() {

    private var moviesViewModels: ObservableArrayList<MovieViewModel> = ObservableArrayList()

    @Bindable
    fun getMoviesViewModels(): ObservableArrayList<MovieViewModel> {
        return moviesViewModels
    }

    fun setMoviesViewModels(moviesViewModelsList: ObservableArrayList<MovieViewModel>) {
        moviesViewModels = moviesViewModelsList
        notifyPropertyChanged(BR.moviesViewModel)
    }

    fun updateMovies() {
        MoviesRepository().getMovies(SortTypes.PopularDec, 1) { movies, error ->
            if (movies != null && error == null) {
                fromArray(movies).flatMapIterable { movies -> movies }
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