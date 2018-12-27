package com.mes.example.kotlinmovieapp.models

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    val page: Int = 0,
    @SerializedName("results")
    val movies: List<Movie>?,
    @SerializedName("total_pages")
    val totalPages: Int = 0
)