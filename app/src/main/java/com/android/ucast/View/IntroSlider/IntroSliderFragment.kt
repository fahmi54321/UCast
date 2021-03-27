package com.android.ucast.View.IntroSlider

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager.findFragment
import androidx.fragment.app.replace
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.android.ucast.R
import com.android.ucast.databinding.FragmentIntroSliderBinding


class IntroSliderFragment : Fragment(), View.OnClickListener {
    lateinit var binding: FragmentIntroSliderBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentIntroSliderBinding.inflate(LayoutInflater.from(context),container, false)
//        return inflater.inflate(R.layout.fragment_intro_slider, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       binding.btnLanjut.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_lanjut) {
            val introSlider1Fragment = IntroSlider1Fragment()
            val fragmentManager = fragmentManager
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.frame_container, introSlider1Fragment, IntroSlider1Fragment::class.java.simpleName )
                addToBackStack(null)
                commit()      
            }

        }
    }


}

