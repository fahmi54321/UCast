package com.android.ucast.View.loginpage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.ucast.R
import dagger.android.support.DaggerAppCompatActivity

class LoginActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}