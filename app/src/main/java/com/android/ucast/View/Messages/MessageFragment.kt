package com.android.ucast.View.Messages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.ucast.Adapter.Schedule.DataLoadStateAdapter
import com.android.ucast.Adapter.message.AdapterMessage
import com.android.ucast.Di.ViewModel.ViewModelProviderFactory
import com.android.ucast.Model.Messages
import com.android.ucast.ViewModel.ViewModelUCase
import com.android.ucast.databinding.FragmentMessageBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.layout_sheet_messages.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class MessageFragment : DaggerFragment() {

    lateinit var viewModel : ViewModelUCase
    lateinit var adapterMessage: AdapterMessage
    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
//    lateinit var sheetBehavior: BottomSheetBehavior<*>
    lateinit var binding: FragmentMessageBinding

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

        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(ViewModelUCase::class.java)
        adapterMessage = AdapterMessage()
        viewModel.setMessage()
        startAdapter()
        startJob()

//        sheetBehavior = BottomSheetBehavior.from(bottomsheett)
//        sheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

//        binding.addMessages.setOnClickListener {
//            btnMessage.text = "Tambah"
//            edtSubjek.setText("")
//            edtTulisPesan.setText("")
//            edtSubjek.setHint("Subjek")
//            edtTulisPesan.setHint("Tulis Pesan")
//            sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
//        }
//        txtAddMessages.setOnClickListener {
//            sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
//        }


    }

    private fun startJob() {
      lifecycleScope.launch {
          viewModel.getMessage()?.collect {
              adapterMessage.submitData(it)
          }
      }
    }

    private fun startAdapter() {
        binding.rvMessages.apply {
            layoutManager = LinearLayoutManager(context)

        }
        binding.rvMessages.adapter = adapterMessage.withLoadStateFooter(
                footer = DataLoadStateAdapter() {
                    retry()
                }
        )
    }

    private fun retry() {
        adapterMessage.retry()
    }

//    private fun showBottomSheet(item: Messages) {
//        sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
//        edtSubjek.setText(item.judul)
//        edtTulisPesan.setText(item.isi)
//        btnMessage.text = "Edit"
//    }
}