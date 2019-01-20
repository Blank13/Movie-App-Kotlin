package com.mes.example.kotlinmovieapp.view.moviedetail

import android.databinding.DataBindingUtil
import android.databinding.ObservableArrayList
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mes.example.kotlinmovieapp.R
import com.mes.example.kotlinmovieapp.databinding.ItemTrailerBinding
import com.mes.example.kotlinmovieapp.viewmodels.TrailerViewModel

class TrailersRecyclerViewAdapter : RecyclerView.Adapter<TrailerViewHolder>() {

    var trailersViewModels: ObservableArrayList<TrailerViewModel> = ObservableArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): TrailerViewHolder {
        val itemBinding = DataBindingUtil.inflate<ItemTrailerBinding>(LayoutInflater.from(parent.context),
            R.layout.item_trailer, parent, false)
        return TrailerViewHolder(itemBinding.root)
    }

    override fun getItemCount(): Int {
        return trailersViewModels.size
    }

    override fun onBindViewHolder(trailerViewHolder: TrailerViewHolder, position: Int) {
        trailerViewHolder.binding?.trailerViewModel = trailersViewModels[position]
    }
}