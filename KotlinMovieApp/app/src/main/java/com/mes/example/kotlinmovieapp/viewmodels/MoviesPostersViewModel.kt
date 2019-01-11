package com.mes.example.kotlinmovieapp.viewmodels

import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.util.Log
import android.widget.Toast
import com.mes.example.kotlinmovieapp.MovieApp
import com.mes.example.kotlinmovieapp.data.MoviesRepository
import com.mes.example.kotlinmovieapp.utils.LOGGER_TAG
import com.mes.example.kotlinmovieapp.utils.SortTypes
import io.reactivex.Observable.fromArray
import java.io.Serializable

class MoviesPostersViewModel: Serializable {

    var moviesViewModels: ObservableArrayList<MovieViewModel> = ObservableArrayList()
    var isLoading = ObservableBoolean(false)
    var sortType = SortTypes.PopularDec
    private var lastSortType = SortTypes.PopularDec
    private var pageNumber = 1

    fun updateMovies() {
        if (sortType != lastSortType) {
            lastSortType = sortType
            moviesViewModels.clear()
            pageNumber = 1
        }
        isLoading.set(true)
        MoviesRepository().getMovies(pageNumber, sortType,
            { movies ->
                fromArray(movies).flatMapIterable { movies }
                    .map { MovieViewModel(it) }
                    .toList()
                    .subscribe { moviesList -> moviesViewModels.addAll(moviesList) }
                isLoading.set(false)
            }, { error ->
                Log.e(LOGGER_TAG, error)
                Toast.makeText(MovieApp.context, error, Toast.LENGTH_LONG).show()
                isLoading.set(false)
            })
    }

    fun getMovieViewModelForPosterAt(position: Int) : MovieViewModel {
        return moviesViewModels[position]
    }
}