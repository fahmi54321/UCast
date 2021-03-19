package com.android.ucast.View.Customers

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import com.android.ucast.Adapter.Customers.ListItemCustomersAdapter
import com.android.ucast.Model.Customers.DataItem
import com.android.ucast.Model.Customerss
import com.android.ucast.R
import com.android.ucast.View.HomeActivity
import com.android.ucast.databinding.ActivityDetailsCustomerBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.android.support.DaggerAppCompatActivity
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

    lateinit var binding: ActivityDetailsCustomerBinding
    private var item: ArrayList<DataItem> = ArrayList()

    // deklarasi animasi
    lateinit var top_to_bottom : Animation
    lateinit var bottom_to_top : Animation
    lateinit var left_to_right : Animation
    lateinit var right_to_left : Animation

    @Inject
    lateinit var dateFormatter: SimpleDateFormat

    var datePickerDialog: DatePickerDialog? = null
    lateinit var sheetBehavior: BottomSheetBehavior<*>
    lateinit var sheetBehaviorSuccess: BottomSheetBehavior<*>

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsCustomerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAnimasi()
        initBottomSheet()
        startAnimasi()
        aksiTombol()
        showDetalisCustomers()
        showSpinner()

    }

    private fun startAnimasi() {
        binding.linearLayoutBack.startAnimation(left_to_right)
        binding.rvItemCustomers.startAnimation(left_to_right)
        binding.btnSendSchedule.startAnimation(bottom_to_top)
        binding.btnSchedule.startAnimation(bottom_to_top)
        binding.spinner.startAnimation(left_to_right)
    }

    private fun showSpinner() {
        val messageSpinner: ArrayAdapter<*> =
                ArrayAdapter<Any?>(this, android.R.layout.simple_spinner_item, message)
        messageSpinner.setDropDownViewResource(R.layout.custom_spinner)
        binding.spinner.adapter = messageSpinner
    }

    private fun showDetalisCustomers() {
        item = intent.getParcelableArrayListExtra<DataItem>("data") as ArrayList<DataItem>
        var listItemCustomersAdapter = ListItemCustomersAdapter(item)
        binding.rvItemCustomers.adapter = listItemCustomersAdapter
    }

    private fun aksiTombol() {

        binding.btnSchedule.setOnClickListener {
            sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            txtSchedule.startAnimation(left_to_right)
            rgSchedule.startAnimation(left_to_right)
            txtCustom.startAnimation(left_to_right)
            relativeAturTanggal.startAnimation(left_to_right)
            btnBuat.startAnimation(left_to_right)
        }

        binding.imageViewBack.setOnClickListener {
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }

        binding.btnSendSchedule.setOnClickListener {
            txtSukses.startAnimation(top_to_bottom)
            imgIconSuccess.startAnimation(top_to_bottom)
            txtTitleSuccess.startAnimation(bottom_to_top)
            txtSubtitleSuccess.startAnimation(bottom_to_top)
            btnKirimLagi.startAnimation(bottom_to_top)
            sheetBehaviorSuccess.state = BottomSheetBehavior.STATE_EXPANDED
        }

        imgDate.setOnClickListener {
            showDate()
        }
    }

    private fun initBottomSheet() {
        sheetBehavior = BottomSheetBehavior.from(bottomsheett)
        sheetBehaviorSuccess = BottomSheetBehavior.from(bottomsheettSuccess)
        sheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        sheetBehaviorSuccess.state = BottomSheetBehavior.STATE_HIDDEN
    }

    private fun initAnimasi() {
        top_to_bottom = AnimationUtils.loadAnimation(this, R.anim.top_to_bottom)
        bottom_to_top = AnimationUtils.loadAnimation(this, R.anim.bottom_to_top)
        left_to_right = AnimationUtils.loadAnimation(this, R.anim.left_to_right)
        right_to_left = AnimationUtils.loadAnimation(this, R.anim.right_to_left)
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