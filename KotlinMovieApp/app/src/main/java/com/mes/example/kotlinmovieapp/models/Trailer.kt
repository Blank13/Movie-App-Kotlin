package com.mes.example.kotlinmovieapp.models

//import org.parceler.Parcel
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey
import java.io.Serializable

open class Trailer(
    @PrimaryKey
    var id: String = "",
    var key: String = "",
    var name: String = "",
    @LinkingObjects("trailers")
    val movie: RealmResults<Movie>? = null
): RealmObject(), Serializable {}