package com.alexfacciorusso.architecturedemo.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.alexfacciorusso.architecturedemo.usecase.LoginResult
import com.alexfacciorusso.architecturedemo.usecase.LoginUseCase
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ActivityScoped
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {
    private val _loggedInState = MutableStateFlow<LoginViewState>(LoginViewState.Initial)

    val loggedInState: LiveData<LoginViewState> = _loggedInState.asLiveData()

    fun submitLogin(username: String, password: String) {
        viewModelScope.launch {
            _loggedInState.value = when (loginUseCase(username, password)) {
                LoginResult.Failure -> LoginViewState.Failure
                is LoginResult.Success -> LoginViewState.Success
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        _loggedInState.value = LoginViewState.Initial
    }
}

sealed interface LoginViewState {
    object Initial : LoginViewState
    object Success : LoginViewState
    object Failure : LoginViewState
}