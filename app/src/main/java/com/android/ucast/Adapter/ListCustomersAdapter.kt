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
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ListCustomersAdapter(
    var data: List<Customers>,
    var onClick: onClickListener
) : RecyclerView.Adapter<ListCustomersAdapter.CustomersViewHolder>() {
    class CustomersViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var gambar = view.findViewById<ImageView>(R.id.imgGambar)
        var nama = view.findViewById<TextView>(R.id.txtNama)
        var noHp = view.findViewById<TextView>(R.id.txtNoHp)
        var chkChose = view.findViewById<CheckBox>(R.id.chkChose)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomersViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_customers, parent, false)
        return CustomersViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomersViewHolder, position: Int) {
        var item = data.get(position)
        holder.nama.text = item.nama
        holder.noHp.text = item.noHp
        holder.chkChose.isChecked = false
        holder.view.setOnClickListener {
            onClick.details(item)
        }

    }

    override fun getItemCount(): Int = data.size

    interface onClickListener {
        fun check(item: Customers)
        fun details(item: Customers)
    }
}