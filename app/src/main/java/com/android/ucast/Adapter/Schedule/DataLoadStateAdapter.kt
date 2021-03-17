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
import com.android.ucast.databinding.ItemLoadingBinding

class DataLoadStateAdapter(val retry: () -> Unit) :
    LoadStateAdapter<DataLoadStateAdapter.DataLoadStataeAdaperViewHolder>() {
    class DataLoadStataeAdaperViewHolder(var binding: ItemLoadingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            binding.progressBar.isVisible = loadState is LoadState.Loading
        }
    }

    override fun onBindViewHolder(holder: DataLoadStataeAdaperViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): DataLoadStataeAdaperViewHolder {
        var view = ItemLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataLoadStataeAdaperViewHolder(view)
    }
}