package com.mes.example.kotlinmovieapp.view.moviedetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.mes.example.kotlinmovieapp.R
import com.mes.example.kotlinmovieapp.utils.DETAIL_FRAGMENT_TAG
import com.mes.example.kotlinmovieapp.utils.getFragment
import com.mes.example.kotlinmovieapp.viewmodels.MovieDetailViewModel
import com.mes.example.kotlinmovieapp.viewmodels.MovieViewModel

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        val movieDetailFragment = getFragment(supportFragmentManager, DETAIL_FRAGMENT_TAG) ?: MovieDetailFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.movie_detail_container, movieDetailFragment, DETAIL_FRAGMENT_TAG)
            .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
