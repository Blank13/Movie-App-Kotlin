package com.mes.example.kotlinmovieapp.viewmodels

import android.annotation.SuppressLint
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.util.Log
import com.mes.example.kotlinmovieapp.data.MoviesRepository
import com.mes.example.kotlinmovieapp.delegates.MoviesPostersFragmentDelegate
import com.mes.example.kotlinmovieapp.utils.LOGGER_TAG
import com.mes.example.kotlinmovieapp.utils.SortTypes
import io.reactivex.Observable.fromArray
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.Serializable

class MoviesPostersViewModel: Serializable {

    var moviesViewModels: ObservableArrayList<MovieViewModel> = ObservableArrayList()
    var isLoading = ObservableBoolean(false)
    var sortType = SortTypes.PopularDec
    var moviesPostersFragmentDelegate: MoviesPostersFragmentDelegate? = null
    var isLoaderNeeded = ObservableBoolean(true)

    private val moviesRepository = MoviesRepository()
    private var lastSortType = SortTypes.PopularDec
    private var pageNumber = 1

    @SuppressLint("CheckResult")
    fun getMovies(){
        lastSortType = sortType
        moviesViewModels.clear()
        isLoaderNeeded.set(true)
        val movies = moviesRepository.getMovies()
        fromArray(movies).flatMapIterable { movies }
            .map { MovieViewModel(it) }
            .toList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { moviesList ->
                moviesViewModels.addAll(moviesList)
                isLoaderNeeded.set(false)
            }
        isLoading.set(false)

    }

    fun updateMovies() {
        if (sortType != lastSortType) {
            lastSortType = sortType
            moviesViewModels.clear()
            pageNumber = 1
        }
        isLoading.set(true)
        isLoaderNeeded.set(true)
        println("Going to Request Page: $pageNumber")
        moviesRepository.updateMovies(pageNumber, sortType,
            { movies ->
                fromArray(movies).flatMapIterable { movies }
                    .map { MovieViewModel(it) }
                    .toList()
                    .subscribe { moviesList ->
                        moviesViewModels.addAll(moviesList)
                    }
                isLoading.set(false)
                println("Finished Requesting Page: $pageNumber")
                pageNumber++
            }, { error ->
                Log.e(LOGGER_TAG, error)
                println(error)
                moviesPostersFragmentDelegate?.onGetingMoviesError(error)
                isLoaderNeeded.set(false)
                isLoading.set(false)
            })
    }

    fun getMovieDetailViewModelForPosterAt(position: Int) : MovieDetailViewModel {
        return MovieDetailViewModel(moviesViewModels[position].getMovie())
    }
}