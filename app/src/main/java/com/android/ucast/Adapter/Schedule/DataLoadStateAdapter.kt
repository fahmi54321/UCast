package com.android.ucast.Adapter.Schedule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.ucast.R

class DataLoadStateAdapter(val retry: () -> Unit) :
    LoadStateAdapter<DataLoadStateAdapter.DataLoadStataeAdaperViewHolder>() {
    class DataLoadStataeAdaperViewHolder(var itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(loadState: LoadState) {
            var progress = itemView.findViewById<ProgressBar>(R.id.progressBar)
            progress.isVisible = loadState is LoadState.Loading
        }
    }

    override fun onBindViewHolder(holder: DataLoadStataeAdaperViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): DataLoadStataeAdaperViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false)

        return DataLoadStataeAdaperViewHolder(view)
    }
}