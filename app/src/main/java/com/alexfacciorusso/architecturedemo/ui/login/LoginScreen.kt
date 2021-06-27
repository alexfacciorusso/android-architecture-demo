package com.alexfacciorusso.architecturedemo.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alexfacciorusso.architecturedemo.R
import com.alexfacciorusso.architecturedemo.login.LoginViewModel
import com.alexfacciorusso.architecturedemo.login.LoginViewState
import com.alexfacciorusso.architecturedemo.ui.theme.MyApplicationTheme
import com.alexfacciorusso.architecturedemo.usecase.LoginResult
import com.alexfacciorusso.architecturedemo.usecase.LoginUseCase

@Composable
fun LoginScreen(viewModel: LoginViewModel = viewModel(), onSuccess: () -> Unit = {}) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val state by viewModel.loggedInState.collectAsState()

    val isError = state is LoginViewState.Failure

    if (state is LoginViewState.Success) {
        SideEffect {
            onSuccess()
            viewModel.useSuccess()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 16.dp, vertical = 24.dp),
    ) {
        TextField(
            value = username,
            onValueChange = { username = it },
            label = {
                Text(if (!isError) stringResource(R.string.hint_username) else stringResource(id = R.string.error_invalid))
            },
            modifier = Modifier.fillMaxWidth(),
            isError = state is LoginViewState.Failure,
        )

        TextField(
            value = password,
            onValueChange = { password = it },
            label = {
                Text(if (!isError) stringResource(R.string.hint_password) else stringResource(id = R.string.error_invalid))
            },
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            isError = isError,
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(onClick = {
                viewModel.submitLogin(username, password)
            }, modifier = Modifier.padding(top = 16.dp)) {
                Text(stringResource(id = R.string.button_login))
            }
        }
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    MyApplicationTheme {
        LoginScreen(viewModel = LoginViewModel(object : LoginUseCase {
            override suspend fun invoke(username: String, password: String): LoginResult =
                LoginResult.Success("123")
        }))
    }
}