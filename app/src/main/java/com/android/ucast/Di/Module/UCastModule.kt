package com.android.ucast.Di.Module

import android.view.View
import com.android.ucast.DataSource.DataSource
import com.android.ucast.Network.ConfigApi
import com.android.ucast.Repository.Repository
import com.android.ucast.View.Customers.DetailsCustomerActivity
import com.android.ucast.View.Messages.MessageFragment
import com.android.ucast.ViewModel.ViewModelUCase
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Singleton

@Module
abstract class UCastModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideConfigApi(retrofit: Retrofit): ConfigApi = retrofit.create(ConfigApi::class.java)

        @JvmStatic
        @Provides
        fun profideDataSource(api: ConfigApi):DataSource = DataSource(api)

        @JvmStatic
        @Provides
        fun profideRepository(api: ConfigApi):Repository = Repository(api)

        @JvmStatic
        @Provides
        fun profideViewModel(repository: Repository):ViewModelUCase = ViewModelUCase(repository)

        //TODO 11
        @JvmStatic
        @Provides
        fun profideSimpleDateFormat(): SimpleDateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.US)
    }
}