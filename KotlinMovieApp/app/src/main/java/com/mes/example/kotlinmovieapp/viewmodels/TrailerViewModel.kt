package com.mes.example.kotlinmovieapp.viewmodels

import com.mes.example.kotlinmovieapp.models.Trailer
import com.mes.example.kotlinmovieapp.utils.YOUTUBE_THUMBNAIL_BASE_URL
import com.mes.example.kotlinmovieapp.utils.YOUTUBE_THUMBNAIL_IMGNUM_URL

class TrailerViewModel(private val trailer: Trailer) {

    val name = trailer.name

    fun getTrailerUrl(): String{
        return YOUTUBE_THUMBNAIL_BASE_URL + trailer.key + YOUTUBE_THUMBNAIL_IMGNUM_URL
    }
}