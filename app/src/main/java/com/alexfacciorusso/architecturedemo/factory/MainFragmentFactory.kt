package com.alexfacciorusso.architecturedemo.factory

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.alexfacciorusso.architecturedemo.ui.SuccessFragment
import com.alexfacciorusso.architecturedemo.ui.login.LoginFragment
import javax.inject.Inject

class MainFragmentFactory @Inject constructor(
    private val viewModelProvider: ViewModelProvider.Factory,
) : FragmentFactory() {
    override fun instantiate(
        classLoader: ClassLoader,
        className: String,
    ): Fragment = when (className) {
        LoginFragment::class.java.name -> LoginFragment(viewModelProvider)
        SuccessFragment::class.java.name -> SuccessFragment()
        else -> super.instantiate(classLoader, className)
    }
}