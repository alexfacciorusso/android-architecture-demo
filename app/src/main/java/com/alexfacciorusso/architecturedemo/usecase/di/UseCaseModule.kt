package com.alexfacciorusso.architecturedemo.usecase.di

import com.alexfacciorusso.architecturedemo.usecase.LoginUseCase
import com.alexfacciorusso.architecturedemo.usecase.LoginUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Binds
    internal abstract fun loginUseCase(loginUseCase: LoginUseCaseImpl): LoginUseCase
}