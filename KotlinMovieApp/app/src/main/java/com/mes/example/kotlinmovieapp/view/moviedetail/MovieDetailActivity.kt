package com.mes.example.kotlinmovieapp.view.moviedetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mes.example.kotlinmovieapp.R
import com.mes.example.kotlinmovieapp.viewmodels.MovieDetailViewModel
import com.mes.example.kotlinmovieapp.viewmodels.MovieViewModel

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        val movieDetailFragment = MovieDetailFragment()
        if (intent != null && intent.extras != null && intent.hasExtra("MovieViewModel")) {
            val movieDetailViewModel = intent.extras["MovieViewModel"] as MovieViewModel?
            movieDetailFragment.movieDetailViewModel = MovieDetailViewModel(movieDetailViewModel)
        }
        supportFragmentManager
            .beginTransaction()
            .add(R.id.movie_detail_container, movieDetailFragment, "MOVIE_DETAIL_FRAGMENT")
            .commit()
    }
}
