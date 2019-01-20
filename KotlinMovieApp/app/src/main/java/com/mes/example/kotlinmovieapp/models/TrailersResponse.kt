package com.mes.example.kotlinmovieapp.models

import com.google.gson.annotations.SerializedName

data class TrailersResponse(
    val id: Int,
    @SerializedName("results")
    val trailers: List<Trailer>
)