package com.android.ucast.View.IntroSlider

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import com.android.ucast.R
import com.android.ucast.databinding.FragmentIntroSlider1Binding

class IntroSlider1Fragment : Fragment(), View.OnClickListener {
    lateinit var binding : FragmentIntroSlider1Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding = FragmentIntroSlider1Binding.inflate(LayoutInflater.from(context), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnlanjut1.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btnlanjut1){
            val introSlider2Fragment = IntroSlider2Fragment()
            val fragmentManager = fragmentManager
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.frame_container, introSlider2Fragment, IntroSlider2Fragment::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }
    }


}