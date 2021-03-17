package com.android.ucast.View

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.android.ucast.R
import com.android.ucast.View.Customers.ListCostumerFragment
import com.android.ucast.View.Messages.MessageFragment
import com.android.ucast.View.Profile.ProfileFragment
import com.android.ucast.View.Schedule.ScheduleFragment
import com.android.ucast.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listCostumer = ListCostumerFragment()
        setFragment(listCostumer)

        binding.imgList.setOnClickListener {
            val listCostumer = ListCostumerFragment()
            setFragment(listCostumer)
            changeIcon(binding.imgList, R.drawable.list_active)
            changeIcon(binding.imgMessage, R.drawable.message_in_active)
            changeIcon(binding.imgSchedulers, R.drawable.schedule_in_active)
            changeIcon(binding.imgProfile, R.drawable.profile_in_active)
        }

        binding.imgMessage.setOnClickListener {
            val message = MessageFragment()
            setFragment(message)
            changeIcon(binding.imgList, R.drawable.list_in_active)
            changeIcon(binding.imgMessage, R.drawable.message_active)
            changeIcon(binding.imgSchedulers, R.drawable.schedule_in_active)
            changeIcon(binding.imgProfile, R.drawable.profile_in_active)
        }

        binding.imgSchedulers.setOnClickListener {
            val scheduler = ScheduleFragment()
            setFragment(scheduler)
            changeIcon(binding.imgList, R.drawable.list_in_active)
            changeIcon(binding.imgMessage, R.drawable.message_in_active)
            changeIcon(binding.imgSchedulers, R.drawable.schedule_active)
            changeIcon(binding.imgProfile, R.drawable.profile_in_active)
        }
        binding.imgProfile.setOnClickListener {
            val profile = ProfileFragment()
            setFragment(profile)
            changeIcon(binding.imgList, R.drawable.list_in_active)
            changeIcon(binding.imgMessage, R.drawable.message_in_active)
            changeIcon(binding.imgSchedulers, R.drawable.schedule_in_active)
            changeIcon(binding.imgProfile, R.drawable.profile_active)
        }
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit()
    }

    private fun changeIcon(img: ImageView, drawable: Int) {
        img.setImageResource(drawable)
    }
}