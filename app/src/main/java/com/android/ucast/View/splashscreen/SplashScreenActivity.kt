package com.android.ucast.View.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.android.ucast.R
import com.android.ucast.Session.SessionManager
import com.android.ucast.View.HomeActivity
import com.android.ucast.View.IntroSlider.IntroSliderActivity
import com.android.ucast.View.IntroSlider.IntroSliderFragment
import com.android.ucast.View.loginpage.LoginActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        var sessionManager = SessionManager(this)

        //Splash Screen
        Handler().postDelayed(Runnable {
            if (sessionManager.login ?: true) {
                intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
            else {
                intent = Intent(this, IntroSliderActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 3000)
    }
}