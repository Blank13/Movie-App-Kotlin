package com.mes.example.kotlinmovieapp.view.moviesposters

import android.annotation.SuppressLint
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup
import android.view.*
import android.widget.Toast
import com.jakewharton.rxbinding2.support.v7.widget.scrollStateChanges
import com.mes.example.kotlinmovieapp.R
import com.mes.example.kotlinmovieapp.databinding.FragmentMoviesPostersBinding
import com.mes.example.kotlinmovieapp.delegates.MoviesPostersFragmentDelegate
import com.mes.example.kotlinmovieapp.utils.SortTypes
import com.mes.example.kotlinmovieapp.view.moviedetail.MovieDetailActivity
import com.mes.example.kotlinmovieapp.viewmodels.MoviesPostersViewModel


class MoviesPostersFragment: Fragment(), MoviesPostersFragmentDelegate {

    private lateinit var binding: FragmentMoviesPostersBinding
    private var moviesPostersViewModel: MoviesPostersViewModel = MoviesPostersViewModel()
    private val delegate = object : MoviesPostersFragmentDelegate by this {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    @SuppressLint("CheckResult")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        moviesPostersViewModel.moviesPostersFragmentDelegate = delegate
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies_posters, container, false)
        val layoutManager = GridLayoutManager(context,2)
        layoutManager.spanSizeLookup = object: SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                when (binding.moviesRecyclerview.adapter?.getItemViewType(position) ?: 0) {
                    MoviesPostersRecyclerViewAdapter.PostersViewHolderTypes.Loader.value -> return 2
                    else -> return 1
                }
            }
        }
        binding.moviesRecyclerview.layoutManager = layoutManager
        binding.postersView = this
        binding.moviesViewModel = moviesPostersViewModel
//        moviesPostersViewModel.getMovies()
        moviesPostersViewModel.updateMovies()

        binding.moviesRecyclerview.scrollStateChanges().subscribe {
            if (moviesPostersViewModel.sortType != SortTypes.None) {
                ((binding.moviesRecyclerview.layoutManager) as? GridLayoutManager)?.let { gridLayoutManager ->
                    val totalItemsCount = gridLayoutManager.itemCount
                    val visibleItemsCount = gridLayoutManager.childCount
                    val firstVisibleItem = gridLayoutManager.findFirstVisibleItemPosition()
                    if (visibleItemsCount + firstVisibleItem >= totalItemsCount - 2) {
                        if (!moviesPostersViewModel.isLoading.get()) {
                            moviesPostersViewModel.updateMovies()
                        }
                    }
                }
            }
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_movie_posters, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_popular_movies -> {
                moviesPostersViewModel.sortType = SortTypes.PopularDec
                moviesPostersViewModel.updateMovies()
                return true
            }
            R.id.menu_top_rated_movies -> {
                moviesPostersViewModel.sortType = SortTypes.VoteAverageDec
                moviesPostersViewModel.updateMovies()
                return true
            }
            R.id.menu_favourite_movies -> {
                moviesPostersViewModel.sortType = SortTypes.None
                moviesPostersViewModel.getMovies()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun onPosterSelected(position: Int) {
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra("MovieViewModel", moviesPostersViewModel.getMovieViewModelForPosterAt(position))
        startActivity(intent)
    }

    override fun onGetingMoviesError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

}
