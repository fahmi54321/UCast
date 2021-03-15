package com.android.ucast.Di

import com.android.ucast.Di.Component.DaggerUCastComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class UCastApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerUCastComponent.builder().application(this).build()
    }
}