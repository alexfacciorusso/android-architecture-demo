package com.alexfacciorusso.architecturedemo.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexfacciorusso.architecturedemo.usecase.LoginResult
import com.alexfacciorusso.architecturedemo.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {
    private val _loggedInState = MutableStateFlow<LoginViewState>(LoginViewState.Initial)

    val loggedInState = _loggedInState.asStateFlow()

    fun submitLogin(username: String, password: String) {
        viewModelScope.launch {
            _loggedInState.value = when (loginUseCase(username, password)) {
                LoginResult.Failure -> LoginViewState.Failure
                is LoginResult.Success -> LoginViewState.Success
            }
        }
    }

    fun useSuccess(){
        _loggedInState.value = LoginViewState.Initial
    }
}

sealed interface LoginViewState {
    object Initial : LoginViewState
    object Success : LoginViewState
    object Failure : LoginViewState
}