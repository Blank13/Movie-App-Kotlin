package com.mes.example.kotlinmovieapp.view.moviedetail

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import com.mes.example.kotlinmovieapp.databinding.ItemTrailerBinding

class TrailerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var binding: ItemTrailerBinding? = DataBindingUtil.bind(itemView)
}