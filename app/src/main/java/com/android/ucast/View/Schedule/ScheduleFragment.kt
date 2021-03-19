package com.android.ucast.View.Schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.android.ucast.Adapter.Schedule.DataAdapter
import com.android.ucast.Adapter.DataLoadStateAdapter
import com.android.ucast.Di.ViewModel.ViewModelProviderFactory
import com.android.ucast.Model.DataItem
import com.android.ucast.ViewModel.ViewModelUCase
import com.android.ucast.databinding.FragmentScheduleBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.layout_sheet_schedule.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class ScheduleFragment : DaggerFragment() {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    private var adapter: DataAdapter? = null
    lateinit var viewModel: ViewModelUCase

    lateinit var binding : FragmentScheduleBinding

    lateinit var sheetBehavior: BottomSheetBehavior<*>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sheetBehavior = BottomSheetBehavior.from(bottomsheett)
        sheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        viewModel =
            ViewModelProviders.of(this, providerFactory).get(ViewModelUCase::class.java)
        adapter = DataAdapter(object : DataAdapter.onClickListener {
            override fun details(getItem: DataItem?) {
                sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                edtPenerimaCast.setText(getItem?.firstName)
                edtTitleCast.setText(getItem?.lastName)
                edtIsiCast.setText(getItem?.position)
            }

        })
        viewModel?.setData()

        setupAdapter()
        startJob()

    }

    private fun setupAdapter() {
        binding.rvSchedule.adapter = adapter?.withLoadStateFooter(
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
            viewModel?.getData()?.collectLatest {
                adapter?.submitData(it)
            }
        }
    }
}