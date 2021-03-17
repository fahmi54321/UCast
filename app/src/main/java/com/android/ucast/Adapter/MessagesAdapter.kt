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
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MessagesAdapter(
    var data: List<Messages>,
    var onClick: onClickListener
) : RecyclerView.Adapter<MessagesAdapter.MessagesViewHolder>() {
    class MessagesViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var judul = view.findViewById<TextView>(R.id.txtJudul)
        var isi = view.findViewById<TextView>(R.id.txtIsiPesan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessagesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_messages, parent, false)
        return MessagesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessagesViewHolder, position: Int) {
        var item = data.get(position)
        holder.judul.text = item.judul
        holder.isi.text = item.isi
        holder.view.setOnClickListener {
            onClick.details(item)
        }

    }

    override fun getItemCount(): Int = data.size

    interface onClickListener {
        fun details(item: Messages)
    }
}