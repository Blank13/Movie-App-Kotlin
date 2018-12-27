package com.mes.example.kotlinmovieapp.view.moviedetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mes.example.kotlinmovieapp.R
import com.mes.example.kotlinmovieapp.view.moviesposters.MoviesPostersFragment

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.movie_detail_container, MovieDetailFragment(), "MOVIE_DETAIL_FRAGMENT")
                .commit()
        }
    }
}
