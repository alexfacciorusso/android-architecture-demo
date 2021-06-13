package com.alexfacciorusso.architecturedemo.ui.login.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alexfacciorusso.architecturedemo.usecase.LoginUseCase
import com.alexfacciorusso.architecturedemo.ui.login.LoginViewModel
import javax.inject.Inject

class LoginViewModelFactory @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        LoginViewModel::class.java -> LoginViewModel(loginUseCase)
        else -> throw IllegalStateException()
    } as? T ?: throw IllegalStateException()
}