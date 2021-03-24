package com.android.ucast.Adapter.message

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.ucast.Model.DataMessage
import com.android.ucast.databinding.ItemMessagesBinding
import kotlinx.android.synthetic.main.fragment_message.view.*

class AdapterMessage(val detail: DetailMessage): PagingDataAdapter<DataMessage, AdapterMessage.MessageAdapter>(MessageDiffUtil()) {

    class MessageDiffUtil : DiffUtil.ItemCallback<DataMessage>() {
        override fun areItemsTheSame(oldItem: DataMessage, newItem: DataMessage): Boolean {
            return oldItem.idMessage == newItem.idMessage
        }

        override fun areContentsTheSame(oldItem: DataMessage, newItem: DataMessage): Boolean {
            return oldItem == newItem
        }

    }
    class MessageAdapter(var binding: ItemMessagesBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(dataMessage: DataMessage) {
            binding.txtJudul.text = dataMessage.title
            binding.txtIsiPesan.text = dataMessage.content

        }

    }

    override fun onBindViewHolder(holder: MessageAdapter, position: Int) {
        getItem(position)?.let { holder.bind(it) }
        val data = getItem(position)
        holder.itemView.setOnClickListener {
            data?.let { it1 ->
                detail.detailMessage(
                    it1
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageAdapter {
        val bindingView = ItemMessagesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessageAdapter(bindingView)
    }

    interface DetailMessage{
        fun detailMessage(dataMessage: DataMessage)
    }

}


