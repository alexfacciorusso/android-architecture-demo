package com.alexfacciorusso.architecturedemo.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.alexfacciorusso.architecturedemo.R
import com.alexfacciorusso.architecturedemo.databinding.LoginFragmentBinding

class LoginFragment(
    viewModelFactory: ViewModelProvider.Factory,
) : Fragment(R.layout.login_fragment) {

    private val viewModel by viewModels<LoginViewModel> { viewModelFactory }
    private var _bindings: LoginFragmentBinding? = null
    private val bindings get() = _bindings ?: throw IllegalAccessError()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _bindings = LoginFragmentBinding.bind(view)

        with(bindings) {
            loginButton.setOnClickListener {
                viewModel.submitLogin(
                    usernameTextInput.text?.toString().orEmpty(),
                    passwordTextInput.text?.toString().orEmpty(),
                )
            }
        }

        viewModel.loggedInState.observe(viewLifecycleOwner) {
            when (it) {
                LoginViewState.Initial -> {
                    setErrors(null)
                }
                LoginViewState.Success -> {
                    setErrors(null)
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSuccessFragment())
                }
                LoginViewState.Failure -> {
                    setErrors(R.string.error_invalid)
                }
            }
        }
    }

    private fun setErrors(errorRes: Int?) {
        val error = errorRes?.let { getString(it) }
        bindings.usernameInputLayout.error = error
        bindings.passwordInputLayout.error = error
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bindings = null
    }
}