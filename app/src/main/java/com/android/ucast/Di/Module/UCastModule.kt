package com.android.ucast.Di.Module

import com.android.ucast.DataSource.DataSource
import com.android.ucast.DataSource.DataSourceCustomers
import com.android.ucast.DataSource.DataSourceMessages
import com.android.ucast.Network.ConfigApi
import com.android.ucast.Repository.Repository
import com.android.ucast.ViewModel.ViewModelUCase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import java.text.SimpleDateFormat
import java.util.*

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
        fun profideDataSourceMessages(api: ConfigApi):DataSourceMessages = DataSourceMessages(api)

        @JvmStatic
        @Provides
        fun profideDataSourceCustomers(api: ConfigApi):DataSourceCustomers = DataSourceCustomers(api)

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