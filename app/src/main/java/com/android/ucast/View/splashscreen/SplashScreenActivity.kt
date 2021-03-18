package com.android.ucast.View.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.android.ucast.R
import com.android.ucast.View.IntroSlider.IntroSliderActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        //Splash Screen
        Handler().postDelayed(Runnable {
            intent = Intent(this, IntroSliderActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}