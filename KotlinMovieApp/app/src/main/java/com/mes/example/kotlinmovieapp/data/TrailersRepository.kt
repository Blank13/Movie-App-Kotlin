package com.mes.example.kotlinmovieapp.data

import android.annotation.SuppressLint
import com.mes.example.kotlinmovieapp.models.Trailer
import com.mes.example.kotlinmovieapp.networking.RetrofitHelper
import com.mes.example.kotlinmovieapp.networking.TrailersApiHelper
import com.mes.example.kotlinmovieapp.utils.BASE_URL
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.Serializable

class TrailersRepository : Serializable {

    @SuppressLint("CheckResult")
    fun getTrailerForMovie(id: Int,
                           onSuccess: (trailers: List<Trailer>) -> Unit,
                           onError: (error: String) -> Unit) {
        val trailersApiHelper = RetrofitHelper.getRetrofitForUrl(BASE_URL).create(TrailersApiHelper::class.java)
        trailersApiHelper.getTrailersForMovie(id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { trailersResponse ->
                    if (id == trailersResponse.id) {
                        onSuccess(trailersResponse.trailers)
                    }
                    else{
                        onError("Recived Response with different Movie Id." +
                                "\t it should be $id but was ${trailersResponse.id} ")
                    }
                }, { error ->
                    onError(error.localizedMessage)
                }
            )
    }
}