package com.android.ucast.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.ucast.Model.Customers
import com.android.ucast.Model.Messages
import com.android.ucast.R
import com.android.ucast.databinding.ItemMessagesBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MessagesAdapter(
    var data: List<Messages>,
    var onClick: onClickListener
) : RecyclerView.Adapter<MessagesAdapter.MessagesViewHolder>() {
    class MessagesViewHolder(val binding: ItemMessagesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Messages) {
            binding.txtJudul.text = item.judul
            binding.txtIsiPesan.text = item.isi
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessagesViewHolder {
        val view =
            ItemMessagesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessagesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessagesViewHolder, position: Int) {
        var item = data.get(position)
        holder.bind(item)

        holder.itemView.setOnClickListener {
            onClick.details(item)
        }

    }

    override fun getItemCount(): Int = data.size

    interface onClickListener {
        fun details(item: Messages)
    }
}