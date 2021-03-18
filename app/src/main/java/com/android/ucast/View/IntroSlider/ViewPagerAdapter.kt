package com.android.ucast.View.IntroSlider

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.android.ucast.View.IntroSlider.IntroSlider1Fragment
import com.android.ucast.View.IntroSlider.IntroSlider2Fragment
import com.android.ucast.View.IntroSlider.IntroSliderFragment

class ViewPagerAdapter(FragmentManager: FragmentManager) : FragmentPagerAdapter(FragmentManager) {
   private val fragment = listOf(
        IntroSliderFragment(),
        IntroSlider1Fragment(),
        IntroSlider2Fragment()
    )
    override fun getCount(): Int {
       return fragment.size
    }

    override fun getItem(position: Int): Fragment {
        return fragment[position]
    }
}