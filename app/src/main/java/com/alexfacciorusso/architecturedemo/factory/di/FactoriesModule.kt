package com.alexfacciorusso.architecturedemo.factory.di

import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.alexfacciorusso.architecturedemo.factory.MainFragmentFactory
import com.alexfacciorusso.architecturedemo.factory.MainViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class FactoriesModule {
    @Binds
    abstract fun bindsMainFragmentFactory(fragmentFactory: MainFragmentFactory): FragmentFactory

    @Binds
    abstract fun bindsMainViewModelFactory(viewModelFactory: MainViewModelFactory): ViewModelProvider.Factory
}