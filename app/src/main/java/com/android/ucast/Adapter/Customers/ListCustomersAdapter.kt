package com.android.ucast.Adapter.Customers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.ucast.Model.Customers.DataItem
import com.android.ucast.databinding.ItemCustomersBinding

class ListCustomersAdapter(
        var onClick: onCliclistener
) : PagingDataAdapter<com.android.ucast.Model.Customers.DataItem, ListCustomersAdapter.CustomersViewHolder>(playerDiffUtilCustomers()) {

    var data : ArrayList<DataItem?> = ArrayList()

    class playerDiffUtilCustomers : DiffUtil.ItemCallback<com.android.ucast.Model.Customers.DataItem>() {
        override fun areItemsTheSame(oldItem: com.android.ucast.Model.Customers.DataItem, newItem: com.android.ucast.Model.Customers.DataItem): Boolean {
            return oldItem.idCustomer == newItem.idCustomer
        }

        override fun areContentsTheSame(oldItem: com.android.ucast.Model.Customers.DataItem, newItem: com.android.ucast.Model.Customers.DataItem): Boolean {
            return oldItem == newItem
        }

    }

    class CustomersViewHolder(var binding: ItemCustomersBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: com.android.ucast.Model.Customers.DataItem?) {
            binding.txtNama.text = item?.name
            binding.txtNoHp.text = item?.noHp
        }
    }

    override fun onBindViewHolder(holder: CustomersViewHolder, position: Int) {
        holder.bind(getItem(position))
        var item = getItem(position)

        holder.binding.chkChose.setOnClickListener {
            onClick.add(item,holder)
        }
        holder.itemView.setOnClickListener {
            onClick.data(data)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomersViewHolder {
        val view = ItemCustomersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomersViewHolder(view)
    }

    interface onCliclistener {
        fun add(data:DataItem?,holder: CustomersViewHolder)
        fun data(data:ArrayList<DataItem?>)
    }

}