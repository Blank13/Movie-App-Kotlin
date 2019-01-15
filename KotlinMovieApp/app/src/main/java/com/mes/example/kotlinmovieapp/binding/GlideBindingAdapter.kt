package com.mes.example.kotlinmovieapp.binding

import android.databinding.BindingAdapter
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mes.example.kotlinmovieapp.R
import com.mes.example.kotlinmovieapp.utils.LOGGER_TAG

@BindingAdapter("imageUrl")
fun setImage(imageView: ImageView, url: String) {
    val options = RequestOptions().placeholder(R.drawable.no_photo).error(R.drawable.no_photo)
    Glide.with(imageView.context).applyDefaultRequestOptions(options)
        .load("http://image.tmdb.org/t/p/w185/$url").into(imageView)
    Log.d(LOGGER_TAG, "http://image.tmdb.org/t/p/w185/$url")
}
