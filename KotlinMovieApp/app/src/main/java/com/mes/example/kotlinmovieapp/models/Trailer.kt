package com.mes.example.kotlinmovieapp.models

//import org.parceler.Parcel
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable

open class Trailer(
    @PrimaryKey
    var id: String = "",
    @SerializedName("iso_3166_1")
    var iso31661: String = "",
    @SerializedName("iso_639_1")
    var iso6391: String = "",
    var key: String = "",
    var name: String = "",
    var site: String = "",
    var size: Int = 0,
    var type: String = ""
): RealmObject(), Serializable {}