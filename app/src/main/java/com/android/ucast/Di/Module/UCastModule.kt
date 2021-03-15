package com.android.ucast.Di.Module

import com.android.ucast.Network.ConfigApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
abstract class UCastModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideConfigApi(retrofit: Retrofit): ConfigApi = retrofit.create(ConfigApi::class.java)

    }
}