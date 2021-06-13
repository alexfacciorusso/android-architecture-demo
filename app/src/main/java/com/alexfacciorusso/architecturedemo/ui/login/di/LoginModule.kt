package com.alexfacciorusso.architecturedemo.ui.login.di

import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.alexfacciorusso.architecturedemo.ui.login.factory.LoginFragmentFactory
import com.alexfacciorusso.architecturedemo.ui.login.factory.LoginViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
internal abstract class LoginModule {
    @Binds
    abstract fun bindsLoginFragmentFactory(fragmentFactory: LoginFragmentFactory): FragmentFactory

    @Binds
    abstract fun bindsLoginViewModelFactory(viewModelFactory: LoginViewModelFactory): ViewModelProvider.Factory
}