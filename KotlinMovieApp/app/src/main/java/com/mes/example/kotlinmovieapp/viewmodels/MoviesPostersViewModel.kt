package com.mes.example.kotlinmovieapp.viewmodels

import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.util.Log
import com.mes.example.kotlinmovieapp.data.MoviesRepository
import com.mes.example.kotlinmovieapp.delegates.MoviesPostersFragmentDelegate
import com.mes.example.kotlinmovieapp.utils.LOGGER_TAG
import com.mes.example.kotlinmovieapp.utils.SortTypes
import io.reactivex.Observable.fromArray
import java.io.Serializable

class MoviesPostersViewModel: Serializable {

    var moviesViewModels: ObservableArrayList<MovieViewModel> = ObservableArrayList()
    var isLoading = ObservableBoolean(false)
    var sortType = SortTypes.PopularDec
    var moviesPostersFragmentDelegate: MoviesPostersFragmentDelegate? = null

    var isLoaderNeeded = ObservableBoolean(true)
    private var lastSortType = SortTypes.PopularDec
//    set(value) {
//        if (value == SortTypes.None) {
//            isLoaderNeeded.set(false)
//        }
//        else {
//            isLoaderNeeded.set(true)
//        }
//    }
    private var pageNumber = 1

    fun getMovies(){
        moviesViewModels.clear()
        MoviesRepository().getMoviesFromDB({ movies ->
            fromArray(movies).flatMapIterable { movies }
                .map { MovieViewModel(it) }
                .toList()
                .subscribe { moviesList ->
                    moviesViewModels.addAll(moviesList)
                    isLoaderNeeded.set(false)
                }
            isLoading.set(false)
        },{ error ->
            Log.e(LOGGER_TAG, error)
            println(error)
            moviesPostersFragmentDelegate?.onGetingMoviesError(error)
        })
    }

    fun updateMovies() {
        if (sortType != lastSortType) {
            lastSortType = sortType
            moviesViewModels.clear()
            pageNumber = 1
        }
        isLoading.set(true)
        println("Going to Request Page: $pageNumber")
        MoviesRepository().getMovies(pageNumber, sortType,
            { movies ->
                fromArray(movies).flatMapIterable { movies }
                    .map { MovieViewModel(it) }
                    .toList()
                    .subscribe { moviesList ->
                        moviesViewModels.addAll(moviesList)
                        isLoaderNeeded.set(true)
                    }
                isLoading.set(false)
                println("Finished Requesting Page: $pageNumber")
                pageNumber++
            }, { error ->
                Log.e(LOGGER_TAG, error)
                println(error)
                moviesPostersFragmentDelegate?.onGetingMoviesError(error)
                isLoading.set(false)
            })
    }

    fun getMovieViewModelForPosterAt(position: Int) : MovieViewModel {
        return moviesViewModels[position]
    }
}