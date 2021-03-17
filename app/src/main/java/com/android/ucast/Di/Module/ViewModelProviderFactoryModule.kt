package com.android.ucast.Di.Module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.ucast.Di.ViewModel.ViewModelProviderFactory
import com.android.ucast.ViewModel.ViewModelUCase
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
abstract class ViewModelProviderFactoryModule {

    @Target(
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER
    )
    @MapKey
    internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelProviderFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelUCase::class)
    abstract fun bindViewModelToActivity(viewModel: ViewModelUCase): ViewModel

}