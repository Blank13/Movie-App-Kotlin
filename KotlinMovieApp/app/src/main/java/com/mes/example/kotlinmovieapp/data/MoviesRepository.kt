package com.mes.example.kotlinmovieapp.data

import android.annotation.SuppressLint
import android.util.Log
import com.mes.example.kotlinmovieapp.models.Movie
import com.mes.example.kotlinmovieapp.networking.MoviesApiHelper
import com.mes.example.kotlinmovieapp.networking.RetrofitHelper
import com.mes.example.kotlinmovieapp.utils.BASE_URL
import com.mes.example.kotlinmovieapp.utils.LOGGER_TAG
import com.mes.example.kotlinmovieapp.utils.SortTypes
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers

class MoviesRepository {

    @SuppressLint("CheckResult")
    fun getMovies(pageNumber: Int, sortType: SortTypes = SortTypes.PopularDec,
                  onSuccess: (movies: List<Movie>) -> Unit,
                  onError: (error: String) -> Unit) {
        val moviesApiHelper = RetrofitHelper.getRetrofitForUrl(BASE_URL).create(MoviesApiHelper::class.java)
        moviesApiHelper.getMovies(sortType.value, pageNumber).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { moviesResponse ->
                    onSuccess(moviesResponse.movies ?: listOf())
                },
                { error ->
                    onError(error.localizedMessage)
                }
            )
    }

    @SuppressLint("CheckResult")
    fun getDurationOfMovie(id: Int, onSuccess: (duration: Int) -> Unit, onError: (error: String) -> Unit) {
        val moviesApiHelper = RetrofitHelper.getRetrofitForUrl(BASE_URL).create(MoviesApiHelper::class.java)
        moviesApiHelper.getDurationForMovie(id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { movieDetail ->
                    onSuccess(movieDetail.runtime ?: 0)
                },
                { error ->
                    onError(error.localizedMessage)
                }
            )
    }

}