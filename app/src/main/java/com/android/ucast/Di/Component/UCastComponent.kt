package com.android.ucast.Di.Component

import android.app.Application
import com.android.ucast.Di.Builder.ActivityBuilder
import com.android.ucast.Di.Module.UCastModule
import com.android.ucast.Di.UCastApp
import com.android.ucast.Network.ConfigNetwork
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuilder::class,
        UCastModule::class,
        ConfigNetwork::class
    ]
)
interface UCastComponent : AndroidInjector<UCastApp> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): UCastComponent
    }
}