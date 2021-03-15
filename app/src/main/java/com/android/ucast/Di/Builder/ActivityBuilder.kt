package com.android.ucast.Di.Builder

import com.android.ucast.Di.Module.UCastModule
import com.android.ucast.Di.Scope.Presentation
import com.android.ucast.Repository.Repository
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @Presentation
    @ContributesAndroidInjector(
        modules = [
            UCastModule::class
        ]
    )
    abstract fun contributeRepository(): Repository
}