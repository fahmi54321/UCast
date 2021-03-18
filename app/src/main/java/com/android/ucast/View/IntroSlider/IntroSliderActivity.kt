package com.android.ucast.View.IntroSlider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.android.ucast.R
import com.android.ucast.databinding.ActivityIntroSliderBinding


class IntroSliderActivity : AppCompatActivity() {
   private lateinit var binding: ActivityIntroSliderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_slider)

        //FullScreen Windows
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()

        //inisialisasi ViewBinding
        binding = ActivityIntroSliderBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Adapter ViewPager
        binding.ViewPager.adapter = ViewPagerAdapter(supportFragmentManager)

    }
}