package com.mes.example.kotlinmovieapp.models

import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.Ignore
import io.realm.annotations.PrimaryKey
import java.io.Serializable

open class Movie(
    @PrimaryKey
    var id: Int = 0,
    var adult: Boolean = false,
    var overview: String = "",
    @Ignore
    var popularity: Double = 0.0,
    @SerializedName("poster_path")
    var posterPath: String = "",
    @SerializedName("release_date")
    var releaseDate: String = "",
    var title: String = "",
    @SerializedName("vote_average")
    var voteAverage: Double = 0.0,
    @SerializedName("vote_count")
    var duration: Int = 0,
    var trailers: RealmList<Trailer>? = null
) : RealmObject(), Serializable {}