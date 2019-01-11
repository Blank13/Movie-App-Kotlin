package com.mes.example.kotlinmovieapp.networking

import com.mes.example.kotlinmovieapp.models.MovieDetail
import com.mes.example.kotlinmovieapp.models.MoviesResponse
import com.mes.example.kotlinmovieapp.utils.API_KEY
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApiHelper {

    @GET("3/discover/movie")
    fun getMovies(@Query("sort_by") sortType: String,
                  @Query("page") pageNumber: Int,
                  @Query("api_key") apiKey: String = API_KEY): Single<MoviesResponse>

    @GET("3/movie/{id}")
    fun getDurationForMovie(@Path("id") id: Int, @Query("api_key") apiKey: String = API_KEY): Single<MovieDetail>

}