package com.android.ucast.View.IntroSlider

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.ucast.R
import com.android.ucast.databinding.FragmentIntroSliderBinding


class IntroSliderFragment : Fragment() {
    lateinit var binding: FragmentIntroSliderBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentIntroSliderBinding.inflate(inflater,container, false)
//        return inflater.inflate(R.layout.fragment_intro_slider, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val introSlider1Fragment = IntroSlider1Fragment()
       binding.btnLanjut.setOnClickListener {
           fragmentManager?.beginTransaction()?.apply {
              add(R.id.container, introSlider1Fragment, IntroSlider1Fragment::class.java.simpleName)
                  .addToBackStack(null)
                  .commit()
           }
        }
    }


}

