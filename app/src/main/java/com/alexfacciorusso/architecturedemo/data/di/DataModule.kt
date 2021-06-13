package com.alexfacciorusso.architecturedemo.data.di

import com.alexfacciorusso.architecturedemo.data.api.FakeLoginApi
import com.alexfacciorusso.architecturedemo.data.api.LoginApi
import com.alexfacciorusso.architecturedemo.data.repository.LoginRepository
import com.alexfacciorusso.architecturedemo.data.repository.LoginRepositoryImpl
import com.alexfacciorusso.architecturedemo.data.repository.TokenRepository
import com.alexfacciorusso.architecturedemo.data.repository.TokenRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    internal abstract fun bindLoginApi(loginApi: FakeLoginApi): LoginApi

    @Binds
    internal abstract fun bindLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository

    @Binds
    @Singleton
    internal abstract fun bindTokenRepository(tokenRepositoryImpl: TokenRepositoryImpl): TokenRepository
}