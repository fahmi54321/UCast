package com.android.ucast.View.Messages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.ucast.Adapter.DataLoadStateAdapter
import com.android.ucast.Adapter.Messages.ListMessagesAdapter
import com.android.ucast.Adapter.MessagesAdapter
import com.android.ucast.Di.ViewModel.ViewModelProviderFactory
import com.android.ucast.Model.Messages
import com.android.ucast.ViewModel.ViewModelUCase
import com.android.ucast.databinding.FragmentMessageBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.layout_sheet_messages.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class MessageFragment : DaggerFragment() {

    lateinit var sheetBehavior: BottomSheetBehavior<*>
    lateinit var binding: FragmentMessageBinding

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    private var adapter: ListMessagesAdapter? = null
    lateinit var viewModel: ViewModelUCase

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sheetBehavior = BottomSheetBehavior.from(bottomsheett)
        sheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        viewModel = ViewModelProvider(this, providerFactory).get(ViewModelUCase::class.java)
        adapter = ListMessagesAdapter()

        binding.addMessages.setOnClickListener {
            btnMessage.text = "Tambah"
            edtSubjek.setText("")
            edtTulisPesan.setText("")
            edtSubjek.setHint("Subjek")
            edtTulisPesan.setHint("Tulis Pesan")
            sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
        txtSukses.setOnClickListener {
            sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        viewModel?.setDataMessages()

        setupAdapter()
        startJob()
    }

    private fun startJob() {
        lifecycleScope.launch {
            viewModel?.getDataMessages()?.collectLatest {
                adapter?.submitData(it)
            }
        }
    }

    private fun setupAdapter() {
        binding?.rvMessages?.apply {
            layoutManager = LinearLayoutManager(activity)
        }
        binding?.rvMessages?.adapter = adapter?.withLoadStateFooter(
                footer = DataLoadStateAdapter() {
                    retry()
                }
        )
    }

    private fun retry() {
        adapter?.retry()
    }

    private fun showBottomSheet(item: Messages) {
        sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        edtSubjek.setText(item.judul)
        edtTulisPesan.setText(item.isi)
        btnMessage.text = "Edit"
    }
}