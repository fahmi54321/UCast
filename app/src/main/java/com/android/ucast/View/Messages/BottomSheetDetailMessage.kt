package com.android.ucast.View.Messages

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.ucast.Model.DataMessage
import com.android.ucast.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_bottom_sheet_detail_message.*


class BottomSheetDetailMessage(): BottomSheetDialogFragment() {

    var dataMessage : DataMessage? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom_sheet_detail_message, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       dataMessage = arguments?.getParcelable("" )
        JudulPesan.setText(dataMessage?.title)
        IsiPesan.setText(dataMessage?.content)

    }


}