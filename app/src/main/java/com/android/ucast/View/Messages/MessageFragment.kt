package com.android.ucast.View.Messages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.ucast.Adapter.Schedule.DataLoadStateAdapter
import com.android.ucast.Adapter.message.AdapterMessage
import com.android.ucast.Di.ViewModel.ViewModelProviderFactory
import com.android.ucast.Model.DataMessage
import com.android.ucast.Model.Messages
import com.android.ucast.R
import com.android.ucast.ViewModel.ViewModelUCase
import com.android.ucast.databinding.FragmentMessageBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_bottom_sheet.*
import kotlinx.android.synthetic.main.layout_sheet_customers.*
import kotlinx.android.synthetic.main.layout_sheet_customers.bottomsheett


import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class MessageFragment : DaggerFragment() {

    lateinit var binding: FragmentMessageBinding
    lateinit var fragment: FragmentBottomSheet
    lateinit var fragmentMessage: BottomSheetDetailMessage
    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    private  var adapterMessage: AdapterMessage? = null
    lateinit var viewModel : ViewModelUCase


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentMessageBinding.inflate(LayoutInflater.from(context), container, false)
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        //inisisialisasi dan memanggil fungsi state hidden
        fragment = FragmentBottomSheet()
        fragmentMessage = BottomSheetDetailMessage()

//        sheetBehavior = BottomSheetBehavior.from(bottomsheett)
//        sheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(ViewModelUCase::class.java)
        adapterMessage = AdapterMessage(object : AdapterMessage.DetailMessage{
            override fun detailMessage(dataMessage: DataMessage) {
                val bottomSheetDetailMessage = BottomSheetDetailMessage()
                val bundle = Bundle()
                bundle.putParcelable("", dataMessage)
                bottomSheetDetailMessage.arguments = bundle
                bottomSheetDetailMessage.show(childFragmentManager, fragment.tag)
            }

        })

        binding.addMessages.setOnClickListener {
//           btnMessage?.text = "Tambah"
//            edtSubjek?.setText("")
//            edtTulisPesan?.setText("")
//            edtSubjek?.setHint("Subjek")
//            edtTulisPesan?.setHint("Tulis Pesan")

            //memanggil fungsi expanded
            fragment.show(childFragmentManager, fragment.tag)
//            sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }



        viewModel.setMessage()
        startAdapter()
        startJob()


    }

    private fun startJob() {
      lifecycleScope.launch {
          viewModel.getMessage()?.collectLatest {
              adapterMessage?.submitData(it)
          }
      }
    }

    private fun startAdapter() {
        binding.rvMessages.apply {
            layoutManager = LinearLayoutManager(context)

        }
        binding.rvMessages.adapter = adapterMessage?.withLoadStateFooter(
                footer = DataLoadStateAdapter() {
                    retry()
                }
        )
    }

    private fun retry() {
        adapterMessage?.retry()
    }

    private fun showBottomSheet(item: Messages) {
        //memanggil fungsi expanded
        fragment.show(childFragmentManager, fragment.tag)
//        sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
//        edtSubjek?.setText(item.judul)
//        edtTulisPesan?.setText(item.isi)
//        btnMessage?.text = "Edit"
    }
}


