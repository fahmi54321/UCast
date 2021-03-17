package com.android.ucast.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.ucast.Model.Customers
import com.android.ucast.R
import com.android.ucast.databinding.ItemCustomersBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ListCustomersAdapter(
    var data: List<Customers>,
    var onClick: onClickListener
) : RecyclerView.Adapter<ListCustomersAdapter.CustomersViewHolder>() {
    class CustomersViewHolder(var binding: ItemCustomersBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomersViewHolder {

        val binding =
            ItemCustomersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomersViewHolder, position: Int) {
        var item = data.get(position)
        holder.binding.txtNama.text = item.nama
        holder.binding.txtNoHp.text = item.noHp
        holder.itemView.setOnClickListener {
            onClick.details(item)
        }


    }

    override fun getItemCount(): Int = data.size

    interface onClickListener {
        fun check(item: Customers)
        fun details(item: Customers)
    }
}