package com.android.ucast.Adapter.Messages

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.ucast.Model.Messagess.DataItemMessages
import com.android.ucast.databinding.ItemMessagesBinding

class ListMessagesAdapter(
) : PagingDataAdapter<DataItemMessages, ListMessagesAdapter.MessagesViewHolder>(playerDiffUtilMessages()) {

    class playerDiffUtilMessages : DiffUtil.ItemCallback<DataItemMessages>() {
        override fun areItemsTheSame(oldItem: DataItemMessages, newItem: DataItemMessages): Boolean {
            return oldItem.idMessage == newItem.idMessage
        }

        override fun areContentsTheSame(oldItem: DataItemMessages, newItem: DataItemMessages): Boolean {
            return oldItem == newItem
        }

    }

    class MessagesViewHolder(var binding: ItemMessagesBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DataItemMessages?) {
            binding.txtJudul.text = item?.title
            binding.txtIsiPesan.text = item?.content
        }
    }

    override fun onBindViewHolder(holder: MessagesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessagesViewHolder {
        val view = ItemMessagesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessagesViewHolder(view)
    }

}