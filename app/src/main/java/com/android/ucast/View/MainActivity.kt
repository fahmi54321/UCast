package com.android.ucast.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.ucast.R
import com.android.ucast.View.IntroSlider.IntroSliderFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        val introSliderFragment = IntroSliderFragment()
        val fragment = fragmentManager.findFragmentByTag(IntroSliderFragment::class.java.simpleName)

        if (fragment !is IntroSliderFragment) {
            fragmentManager.beginTransaction().add(R.id.frame_container, introSliderFragment, IntroSliderFragment::class.java.simpleName)
                    .commit()
        }

    }
}