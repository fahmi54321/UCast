package com.android.ucast.Adapter.Customers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.ucast.Model.Customers.DataItem
import com.android.ucast.Model.Messages
import com.android.ucast.databinding.ItemCustomersDetailsBinding
import com.android.ucast.databinding.ItemMessagesBinding

class ListItemCustomersAdapter(
        var data: List<DataItem>
) : RecyclerView.Adapter<ListItemCustomersAdapter.ItemCustomersViewHolder>() {
    class ItemCustomersViewHolder(val binding: ItemCustomersDetailsBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DataItem) {
            binding.txtNama.text = item.name
            binding.txtNoHp.text = item.noHp
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemCustomersViewHolder {
        val view =
                ItemCustomersDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemCustomersViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemCustomersViewHolder, position: Int) {
        var item = data.get(position)
        holder.bind(item)

    }

    override fun getItemCount(): Int = data.size

}