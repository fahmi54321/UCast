package com.android.ucast.Adapter.Schedule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.ucast.Model.DataItem
import com.android.ucast.R
import com.android.ucast.databinding.ItemScheduleBinding

class DataAdapter(
    var onClick: onClickListener
) : PagingDataAdapter<DataItem, DataAdapter.DataViewHolder>(playerDiffUtil()) {
    class DataViewHolder(var binding: ItemScheduleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(getItem: DataItem?) {
            binding.txtJudul.text = getItem?.firstName
            binding.txtIsiPesan.text = getItem?.lastName
        }

    }

    class playerDiffUtil : DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem == newItem
        }

    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        var data = getItem(position)
        holder.bind(data)
        holder.itemView.setOnClickListener {
            onClick.details(data)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        var view =
            ItemScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(view)
    }

    interface onClickListener {
        fun details(getItem: DataItem?)
    }
}