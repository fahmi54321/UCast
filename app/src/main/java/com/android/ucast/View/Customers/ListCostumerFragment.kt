package com.android.ucast.View.Customers

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.ucast.Adapter.ListCustomersAdapter
import com.android.ucast.Model.Customers
import com.android.ucast.R
import com.android.ucast.databinding.FragmentListCostumerBinding

class ListCostumerFragment : Fragment() {

    lateinit var binding : FragmentListCostumerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListCostumerBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val listCustomer = listOf(
            Customers(R.drawable.fahmi, "Fahmi", "082384863761", 0),
            Customers(R.drawable.yandi, "Yandi", "082384863761", 0),
            Customers(R.drawable.karate, "Karate", "082384863761", 0),
            Customers(R.drawable.kakashi, "Kakashi", "082384863761", 0),
            Customers(R.drawable.fauzi, "Fauzi", "082384863761", 0),
            Customers(R.drawable.johan, "Johan", "082384863761", 0),
        )

        var customerAdapter =
            ListCustomersAdapter(listCustomer, object : ListCustomersAdapter.onClickListener {
                override fun check(item: Customers) {
                    TODO("Not yet implemented")
                }

                override fun details(item: Customers) {
                    val intent = Intent(activity,DetailsCustomerActivity::class.java)
                    intent.putExtra("data",item)
                    startActivity(intent)
                }

            })
        binding.rvCustomers?.apply {
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL, false
            )
            adapter = customerAdapter
        }
    }
}