package com.android.ucast.View.Messages

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.ucast.Di.ViewModel.ViewModelProviderFactory
import com.android.ucast.Model.ResponseInsertMessage
import com.android.ucast.R
import com.android.ucast.Session.SessionManager
import com.android.ucast.ViewModel.ViewModelUCase
import com.android.ucast.databinding.FragmentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_bottom_sheet.*
import kotlinx.android.synthetic.main.fragment_message.*
import kotlinx.android.synthetic.main.layout_sheet_schedule.*
import javax.inject.Inject


class FragmentBottomSheet() : BottomSheetDialogFragment(){
    lateinit var binding: FragmentBottomSheetBinding
    lateinit var viewModelUcast: ViewModelUCase
    @Inject
//    lateinit var factory : ViewModelProvider.Factory
    lateinit var viewModelProvider: ViewModelProviderFactory
    var userId: String? = null
    //Untuk Inject ViewModelProviderFactory
    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_bottom_sheet, container, false)
        binding = FragmentBottomSheetBinding.inflate(LayoutInflater.from(context), container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModelUcast = ViewModelProvider(this, viewModelProvider).get(ViewModelUCase::class.java)
        observeInsert()


        val sessionManager = SessionManager(requireContext())
        if (sessionManager.login == true) {
            userId = sessionManager.id.toString()
        }




        binding.btnMessage.setOnClickListener{

            validation()
        }
    }

    private fun validation() {
        if (edtSubjek.text.toString().isEmpty()) {
            edtSubjek.requestFocus()
            edtSubjek.error = "Tidak Boleh Kosong"
        }else if (edtTulisPesan.text.toString().isEmpty()) {
            edtTulisPesan.requestFocus()
            edtTulisPesan.error = "Tidak Boleh Kosong"
        }else {
            viewModelUcast.insertMessage(edtSubjek.text.toString(), edtTulisPesan.text.toString(), userId.toString())

        }
    }

    private fun observeInsert() {
        viewModelUcast.successResponse().observe(this, Observer { responseSuccess(it) })
        viewModelUcast.isError().observe(this, Observer { responseError(it) })
    }

    private fun responseError(it: Throwable?) {
        Toast.makeText(context, it?.message, Toast.LENGTH_SHORT).show()
    }

    private fun responseSuccess(it: ResponseInsertMessage?) {
        Toast.makeText(context, it?.message, Toast.LENGTH_SHORT).show()
//       if (it?.status == true) {
//
//           Toast.makeText(context, "Berhasil menambahkan Message", Toast.LENGTH_SHORT).show()
//       }
    }


}