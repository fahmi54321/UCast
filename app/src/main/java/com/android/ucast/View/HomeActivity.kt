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
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val listCostumer = ListCostumerFragment()
        setFragment(listCostumer)

        imgList.setOnClickListener {
            val listCostumer = ListCostumerFragment()
            setFragment(listCostumer)
            changeIcon(imgList, R.drawable.list_active)
            changeIcon(imgMessage, R.drawable.message_in_active)
            changeIcon(imgSchedulers, R.drawable.schedule_in_active)
            changeIcon(imgProfile, R.drawable.profile_in_active)
        }

        imgMessage.setOnClickListener {
            val message = MessageFragment()
            setFragment(message)
            changeIcon(imgList, R.drawable.list_in_active)
            changeIcon(imgMessage, R.drawable.message_active)
            changeIcon(imgSchedulers, R.drawable.schedule_in_active)
            changeIcon(imgProfile, R.drawable.profile_in_active)
        }

        imgSchedulers.setOnClickListener {
            val scheduler = ScheduleFragment()
            setFragment(scheduler)
            changeIcon(imgList, R.drawable.list_in_active)
            changeIcon(imgMessage, R.drawable.message_in_active)
            changeIcon(imgSchedulers, R.drawable.schedule_active)
            changeIcon(imgProfile, R.drawable.profile_in_active)
        }
        imgProfile.setOnClickListener {
            val profile = ProfileFragment()
            setFragment(profile)
            changeIcon(imgList, R.drawable.list_in_active)
            changeIcon(imgMessage, R.drawable.message_in_active)
            changeIcon(imgSchedulers, R.drawable.schedule_in_active)
            changeIcon(imgProfile, R.drawable.profile_active)
        }
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit()
    }

    private fun changeIcon(img: ImageView, drawable: Int) {
        img.setImageResource(drawable)
    }
}