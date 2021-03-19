package com.android.ucast.View.Schedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.android.ucast.Adapter.Schedule.DataAdapter
import com.android.ucast.Adapter.Schedule.DataLoadStateAdapter
import com.android.ucast.Di.ViewModel.ViewModelProviderFactory
import com.android.ucast.Model.DataItem
import com.android.ucast.R
import com.android.ucast.ViewModel.ViewModelUCase
import com.android.ucast.databinding.FragmentScheduleBinding
import com.android.ucast.databinding.LayoutSheetScheduleBinding
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

    // deklarasi animasi
    lateinit var top_to_bottom : Animation
    lateinit var bottom_to_top : Animation
    lateinit var left_to_right : Animation
    lateinit var right_to_left : Animation

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

        initAnimate()
        aksiAnimasi()
        initBottomSheet()
        initViewModel()

        adapter = DataAdapter(object : DataAdapter.onClickListener {
            override fun details(getItem: DataItem?) {
                sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                constraintHeader.startAnimation(left_to_right)
                linearPenerimaCost.startAnimation(left_to_right)
                linearTitleCost.startAnimation(left_to_right)
                linearIsiCost.startAnimation(left_to_right)
                linearButton.startAnimation(left_to_right)
                edtPenerimaCast.setText(getItem?.firstName)
                edtTitleCast.setText(getItem?.lastName)
                edtIsiCast.setText(getItem?.position)
            }

        })
        viewModel?.setData()
        setupAdapter()
        startJob()

    }

    private fun aksiAnimasi() {
        binding.rvSchedule.startAnimation(left_to_right)
        binding.relativeSchedule.startAnimation(left_to_right)
    }

    private fun initViewModel() {
        viewModel =
                ViewModelProvider(this, providerFactory).get(ViewModelUCase::class.java)
    }

    private fun initBottomSheet() {
        sheetBehavior = BottomSheetBehavior.from(bottomsheett)
        sheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
    }

    private fun initAnimate() {
        top_to_bottom = AnimationUtils.loadAnimation(context, R.anim.top_to_bottom)
        bottom_to_top = AnimationUtils.loadAnimation(context, R.anim.bottom_to_top)
        left_to_right = AnimationUtils.loadAnimation(context, R.anim.left_to_right)
        right_to_left = AnimationUtils.loadAnimation(context, R.anim.right_to_left)
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