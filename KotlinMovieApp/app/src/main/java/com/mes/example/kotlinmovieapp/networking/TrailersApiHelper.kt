package com.mes.example.kotlinmovieapp.networking

import com.mes.example.kotlinmovieapp.models.TrailersResponse
import com.mes.example.kotlinmovieapp.utils.API_KEY
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TrailersApiHelper {
    @GET("3/movie/{id}/videos")
    fun getTrailersForMovie(@Path("id") id: Int,
                            @Query("api_key") apiKey: String = API_KEY): Single<TrailersResponse>
}