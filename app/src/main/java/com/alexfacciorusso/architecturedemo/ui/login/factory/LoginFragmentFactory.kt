package com.alexfacciorusso.architecturedemo.ui.login.factory

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.alexfacciorusso.architecturedemo.ui.login.LoginFragment
import javax.inject.Inject

class LoginFragmentFactory @Inject constructor(
    private val viewModelProvider: ViewModelProvider.Factory,
) : FragmentFactory() {
    override fun instantiate(
        classLoader: ClassLoader,
        className: String,
    ): Fragment = when (className) {
        LoginFragment::class.java.name -> LoginFragment(viewModelProvider)
        else -> super.instantiate(classLoader, className)
    }
}