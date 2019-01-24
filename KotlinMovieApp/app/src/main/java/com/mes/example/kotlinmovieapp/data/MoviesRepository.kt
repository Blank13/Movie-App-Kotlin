package com.mes.example.kotlinmovieapp.data

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.util.Log
import com.mes.example.kotlinmovieapp.extensions.queryAndUpdateAll
import com.mes.example.kotlinmovieapp.models.Movie
import com.mes.example.kotlinmovieapp.networking.MoviesApiHelper
import com.mes.example.kotlinmovieapp.networking.RetrofitHelper
import com.mes.example.kotlinmovieapp.utils.BASE_URL
import com.mes.example.kotlinmovieapp.utils.LOGGER_TAG
import com.mes.example.kotlinmovieapp.utils.SortTypes
import com.vicpin.krealmextensions.queryAll
import com.vicpin.krealmextensions.queryFirst
import com.vicpin.krealmextensions.save
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import java.io.Serializable

class MoviesRepository : Serializable {

    @SuppressLint("CheckResult")
    fun updateMovies(pageNumber: Int, sortType: SortTypes = SortTypes.PopularDec,
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

    fun saveMovie(movie: Movie){
//        Log.d(LOGGER_TAG, "Realm will execute save from thread: ${Thread.currentThread().name}")
//        Realm.getDefaultInstance().executeTransactionAsync { realm ->
//            realm.insertOrUpdate(movie)
//            Log.d(LOGGER_TAG, "Realm execute save on thread: ${Thread.currentThread().name}")
//        }
        AsyncTask.execute {
            movie.save()
            movie.queryAndUpdateAll({greaterThan("popularity", 150.0)},{
                it.adult = true
            })
        }
    }

    fun getMovieWithId(movieId: Int): Movie? {
//        var movie: Movie? = null
//        try {
//            Log.d(LOGGER_TAG, "Realm will execute check from thread: ${Thread.currentThread().name}")
//            Realm.getDefaultInstance().executeTransaction { realm ->
//                Log.d(LOGGER_TAG, "Realm execute check on thread: ${Thread.currentThread().name}")
//                val result = realm.where(Movie::class.java).equalTo("id", movieId).findFirst()
//                result?.let {
//                    movie = realm.copyFromRealm(it)
//                }
//            }
//
//        } catch (exception: Exception) {
//        }
//        finally {
//            return movie
//        }
        return Movie().queryFirst { equalTo("id", movieId) }
    }

    fun getMovies(): List<Movie> {
//        try{
//            Log.d(LOGGER_TAG, "Realm will execute get from thread: ${Thread.currentThread().name}")
//            Realm.getDefaultInstance().executeTransactionAsync { realm: Realm ->
//                Log.d(LOGGER_TAG, "Realm execute get on thread: ${Thread.currentThread().name}")
//                val result = realm.where(Movie::class.java).findAll()
//                val resultCopy = realm.copyFromRealm(result).toList()
//                onSuccess(resultCopy)
//            }
//        } catch (exception: Exception) {
//            onError(exception.localizedMessage)
//        }
        return Movie().queryAll()
    }

    fun deleteMovie(movieId: Int) {
        try{
            Log.d(LOGGER_TAG, "Realm will execute delete from thread: ${Thread.currentThread().name}")
            Realm.getDefaultInstance().executeTransactionAsync { realm: Realm ->
                Log.d(LOGGER_TAG, "Realm execute delete on thread: ${Thread.currentThread().name}")
                val result = realm.where(Movie::class.java).equalTo("id", movieId).findFirst()
                result?.trailers?.deleteAllFromRealm()
                result?.deleteFromRealm()
            }
        } catch (exception: Exception) {
        }
//        AsyncTask.execute {
////            executeTransaction {
////                getMovieWithId(movieId)?.trailers?.deleteAllFromRealm()
//                Movie().delete { equalTo("id", movieId) }
////            }
//        }
    }

    fun checkIfMovieInFavourite(movieId: Int): Boolean {
//        try {
//            Log.d(LOGGER_TAG, "Realm will execute check from thread: ${Thread.currentThread().name}")
//            var size = 0
//            Realm.getDefaultInstance().executeTransaction { realm ->
//                Log.d(LOGGER_TAG, "Realm execute check on thread: ${Thread.currentThread().name}")
//                val result = realm.where(Movie::class.java).equalTo("id", movieId).findAll()
//                size = result.size
//            }
//            return if (size > 0) true else false
//        } catch (exception: Exception) {
//            return false
//        }
        return getMovieWithId(movieId)!= null
    }

}