package com.android.ucast.Adapter.message

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.ucast.Model.DataMessage
import com.android.ucast.databinding.ItemMessagesBinding
import kotlinx.android.synthetic.main.fragment_message.view.*

class AdapterMessage: PagingDataAdapter<DataMessage, AdapterMessage.MessageAdapter>(MessageDiffUtil()) {
    class MessageAdapter(var binding: ItemMessagesBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(dataMessage: DataMessage) {
            binding.txtJudul.text = dataMessage.title
            binding.txtIsiPesan.text = dataMessage.content

        }

    }

    class MessageDiffUtil : DiffUtil.ItemCallback<DataMessage>() {
        override fun areItemsTheSame(oldItem: DataMessage, newItem: DataMessage): Boolean {
            return oldItem.idMessage == newItem.idMessage
        }

        override fun areContentsTheSame(oldItem: DataMessage, newItem: DataMessage): Boolean {
            return oldItem == newItem
        }

    }

    override fun onBindViewHolder(holder: MessageAdapter, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageAdapter {
        val bindingView = ItemMessagesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessageAdapter(bindingView)
    }

}


