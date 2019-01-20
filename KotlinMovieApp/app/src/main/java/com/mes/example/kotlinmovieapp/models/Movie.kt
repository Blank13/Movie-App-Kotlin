package com.mes.example.kotlinmovieapp.models

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.Ignore
import io.realm.annotations.PrimaryKey
import java.io.Serializable

open class Movie(
    var adult: Boolean = false,
    @SerializedName("backdrop_path")
    var backdropPath: String = "",
    @Ignore
    @SerializedName("genre_ids")
    var genreIds: List<Int?>? = null,
    @PrimaryKey
    var id: Int = 0,
    @Ignore
    @SerializedName("original_language")
    var originalLanguage: String = "",
    @SerializedName("original_title")
    var originalTitle: String = "",
    var overview: String = "",
    var popularity: Double = 0.0,
    @SerializedName("poster_path")
    var posterPath: String = "",
    @SerializedName("release_date")
    var releaseDate: String = "",
    var title: String = "",
    @Ignore
    var video: Boolean = false,
    @SerializedName("vote_average")
    var voteAverage: Double = 0.0,
    @SerializedName("vote_count")
    var voteCount: Int = 0,
    var duration: Int = 0
//    var trailers: RealmList<Trailer>? = null
) : RealmObject(), Serializable {}