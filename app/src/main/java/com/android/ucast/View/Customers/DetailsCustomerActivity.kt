package com.android.ucast.View.Customers

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.ucast.Model.Customers
import com.android.ucast.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_details_customer.*
import kotlinx.android.synthetic.main.layout_sheet_customers.*
import kotlinx.android.synthetic.main.layout_sheet_success_send_schedule.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class DetailsCustomerActivity : DaggerAppCompatActivity() {

    private val message = arrayOf(
        "Pilih Pesan Yang Ingin Kamu Kirim", "Pesan 1", "Pesan 2", "Pesan3",
        "Pesan 4", "Pesan 5", "Pesan 6", "Pesan 7"
    )

    @Inject
    lateinit var dateFormatter: SimpleDateFormat

    var datePickerDialog: DatePickerDialog? = null
    lateinit var sheetBehavior: BottomSheetBehavior<*>
    lateinit var sheetBehaviorSuccess: BottomSheetBehavior<*>

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_customer)

        sheetBehavior = BottomSheetBehavior.from(bottomsheett)
        sheetBehaviorSuccess = BottomSheetBehavior.from(bottomsheettSuccess)
        sheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        sheetBehaviorSuccess.state = BottomSheetBehavior.STATE_HIDDEN

        btnSchedule.setOnClickListener {
            sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        btnSendSchedule.setOnClickListener {
            sheetBehaviorSuccess.state = BottomSheetBehavior.STATE_EXPANDED
        }

        val getData = intent.getParcelableExtra<Customers>("data")
        Glide.with(this)
            .load(getData?.gambar)
            .apply(RequestOptions().error(R.drawable.icon_nopic))
            .into(imgGambar)

        txtNama.text = getData?.nama
        txtNoHp.text = getData?.noHp


        val messageSpinner: ArrayAdapter<*> =
            ArrayAdapter<Any?>(this, android.R.layout.simple_spinner_item, message)
        messageSpinner.setDropDownViewResource(R.layout.custom_spinner)
        spinner.adapter = messageSpinner

        imgDate.setOnClickListener {
            showDate()
        }


    }

    private fun showDate() {
        val newCalendar = Calendar.getInstance()
        datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                val newDate = Calendar.getInstance()
                newDate[year, monthOfYear] = dayOfMonth
                txtDate.text = dateFormatter!!.format(newDate.time)
            },
            newCalendar[Calendar.YEAR],
            newCalendar[Calendar.MONTH],
            newCalendar[Calendar.DAY_OF_MONTH]
        )
        datePickerDialog!!.show()
    }
}