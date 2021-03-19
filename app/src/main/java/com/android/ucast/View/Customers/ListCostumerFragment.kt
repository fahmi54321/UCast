package com.android.ucast.View.Customers

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.ucast.Adapter.Customers.ListCustomersAdapter
import com.android.ucast.Adapter.DataLoadStateAdapter
import com.android.ucast.Di.ViewModel.ViewModelProviderFactory
import com.android.ucast.Model.Customers.DataItem
import com.android.ucast.R
import com.android.ucast.ViewModel.ViewModelUCase
import com.android.ucast.databinding.FragmentListCostumerBinding
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListCostumerFragment : DaggerFragment() {

    lateinit var binding: FragmentListCostumerBinding
    private var item: ArrayList<DataItem?> = ArrayList()

    // deklarasi animasi
    lateinit var top_to_bottom : Animation
    lateinit var bottom_to_top : Animation
    lateinit var left_to_right : Animation
    lateinit var right_to_left : Animation

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    private var adapter: com.android.ucast.Adapter.Customers.ListCustomersAdapter? = null
    lateinit var viewModel: ViewModelUCase

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListCostumerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initAnimasi()

        binding.rvCustomers.startAnimation(left_to_right)
        binding.btnLanjut.startAnimation(right_to_left)

        viewModel = ViewModelProvider(this, providerFactory).get(ViewModelUCase::class.java)
        adapter = ListCustomersAdapter(object : ListCustomersAdapter.onCliclistener {
            override fun add(data: DataItem?, holder: ListCustomersAdapter.CustomersViewHolder) {
                when(holder.binding.chkChose.isChecked){
                    true -> item.add(data)
                    false -> item.remove(data)
                }
            }
        })

        binding.btnLanjut.setOnClickListener {
            val intent = Intent(activity,DetailsCustomerActivity::class.java)
            intent.putParcelableArrayListExtra("data",item)
            startActivity(intent)
        }
        viewModel?.setDataCustomers()

        setupAdapter()
        startJob()
    }

    private fun initAnimasi() {
        top_to_bottom = AnimationUtils.loadAnimation(context, R.anim.top_to_bottom)
        bottom_to_top = AnimationUtils.loadAnimation(context, R.anim.bottom_to_top)
        left_to_right = AnimationUtils.loadAnimation(context, R.anim.left_to_right)
        right_to_left = AnimationUtils.loadAnimation(context, R.anim.right_to_left)
    }

    private fun setupAdapter() {
        binding?.rvCustomers?.apply {
            layoutManager = LinearLayoutManager(activity)
        }
        binding?.rvCustomers?.adapter = adapter?.withLoadStateFooter(
                footer = DataLoadStateAdapter() {
                    retry()
                }
        )
    }

    private fun retry() {
        adapter?.retry()
    }

    private fun startJob() {
        lifecycleScope.launch {
            viewModel?.getDataCustomers()?.collectLatest {
                adapter?.submitData(it)
            }
        }
    }
}