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

class DataAdapter(
    var onClick: onClickListener
) : PagingDataAdapter<DataItem, DataAdapter.DataViewHolder>(playerDiffUtil()) {
    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var judul = itemView.findViewById<TextView>(R.id.txtJudul)
        var isiPesan = itemView.findViewById<TextView>(R.id.txtIsiPesan)

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
        holder.judul.text = data?.firstName
        holder.isiPesan.text = data?.lastName
        holder.itemView.setOnClickListener {
            onClick.details(data)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_schedule, parent, false)
        return DataViewHolder(view)
    }

    interface onClickListener {
        fun details(getItem: DataItem?)
    }
}