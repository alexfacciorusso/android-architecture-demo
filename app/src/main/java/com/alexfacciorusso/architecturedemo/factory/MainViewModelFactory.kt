package com.alexfacciorusso.architecturedemo.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alexfacciorusso.architecturedemo.ui.login.LoginViewModel
import com.alexfacciorusso.architecturedemo.usecase.LoginUseCase
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        LoginViewModel::class.java -> LoginViewModel(loginUseCase)
        else -> throw IllegalStateException()
    } as? T ?: throw IllegalStateException()
}