package com.android.ucast.View.Messages

import android.content.Context
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.ucast.Adapter.message.AdapterMessage
import com.android.ucast.Di.ViewModel.ViewModelProviderFactory
import com.android.ucast.Model.DataMessage
import com.android.ucast.R
import com.android.ucast.ViewModel.ViewModelUCase
import com.android.ucast.databinding.FragmentBottomSheetDetailMessageBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment

import kotlinx.android.synthetic.main.fragment_bottom_sheet_detail_message.*
import javax.inject.Inject


class BottomSheetDetailMessage(): BottomSheetDialogFragment() {

    lateinit var binding: FragmentBottomSheetDetailMessageBinding
    @Inject
    lateinit var providerFactoryViewModel: ViewModelProviderFactory
    lateinit var viewModel : ViewModelUCase
    var dataMessage : DataMessage? = null

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentBottomSheetDetailMessageBinding.inflate(LayoutInflater.from(context), container, false)
        return binding.root
//        return inflater.inflate(R.layout.fragment_bottom_sheet_detail_message, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       dataMessage = arguments?.getParcelable("" )
        JudulPesan.setText(dataMessage?.title)
        IsiPesan.setText(dataMessage?.content)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this, providerFactoryViewModel).get(ViewModelUCase::class.java)
    }








}