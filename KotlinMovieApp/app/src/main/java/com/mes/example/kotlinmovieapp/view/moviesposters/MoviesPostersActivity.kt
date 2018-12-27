package com.mes.example.kotlinmovieapp.view.moviesposters

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.mes.example.kotlinmovieapp.R

class MoviesPostersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_posters)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.movies_posters_container, MoviesPostersFragment(), "MOVIES_POSTERS_FRAGMENT")
                .commit()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_movie_posters, menu)
        return super.onCreateOptionsMenu(menu)
    }

}
