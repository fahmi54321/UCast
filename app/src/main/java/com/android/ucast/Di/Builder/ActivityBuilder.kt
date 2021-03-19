package com.android.ucast.Di.Builder

import com.android.ucast.DataSource.DataSource
import com.android.ucast.Di.Module.UCastModule
import com.android.ucast.Di.Module.ViewModelProviderFactoryModule
import com.android.ucast.Di.Scope.Presentation
import com.android.ucast.Repository.Repository
import com.android.ucast.View.Customers.DetailsCustomerActivity
import com.android.ucast.View.Messages.MessageFragment
import com.android.ucast.View.Schedule.ScheduleFragment
import com.android.ucast.View.loginpage.LoginActivity
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
    abstract fun constributeDetailsCustomers(): DetailsCustomerActivity

    @Presentation
    @ContributesAndroidInjector(
        modules = [
            ViewModelProviderFactoryModule::class
        ]
    )
    abstract fun contributeShedule(): ScheduleFragment


    @Presentation
    @ContributesAndroidInjector(
        modules = [
            ViewModelProviderFactoryModule::class
        ]
    )
    abstract fun contributeLogin(): LoginActivity

    @Presentation
    @ContributesAndroidInjector(
            modules = [
                ViewModelProviderFactoryModule::class
            ]
    )
    abstract fun contributeMessage(): MessageFragment



}