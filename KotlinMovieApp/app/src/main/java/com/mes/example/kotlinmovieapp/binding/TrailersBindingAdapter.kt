package com.mes.example.kotlinmovieapp.binding

import android.databinding.BindingAdapter
import android.databinding.ObservableArrayList
import android.support.v7.widget.RecyclerView
import com.mes.example.kotlinmovieapp.view.moviedetail.TrailersRecyclerViewAdapter
import com.mes.example.kotlinmovieapp.viewmodels.TrailerViewModel

@BindingAdapter("trailerslist")
fun setTrailersItems(recyclerView: RecyclerView, trailersViewModels: ObservableArrayList<TrailerViewModel>) {
    var trailersAdapter: TrailersRecyclerViewAdapter? = recyclerView.adapter as? TrailersRecyclerViewAdapter
    if (trailersAdapter == null) {
        trailersAdapter = TrailersRecyclerViewAdapter()
        recyclerView.adapter = trailersAdapter
        trailersAdapter.trailersViewModels = trailersViewModels
    }
    trailersAdapter.notifyDataSetChanged()
}