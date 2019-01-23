package com.mes.example.kotlinmovieapp.view.moviesposters

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.mes.example.kotlinmovieapp.R
import com.mes.example.kotlinmovieapp.utils.POSTERS_FRAGMENT_TAG
import com.mes.example.kotlinmovieapp.utils.getFragment

class MoviesPostersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_posters)
        val moviesPostersFragment = getFragment(supportFragmentManager, POSTERS_FRAGMENT_TAG) ?: MoviesPostersFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.movies_posters_container, moviesPostersFragment, POSTERS_FRAGMENT_TAG)
            .commit()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return true
    }

}
