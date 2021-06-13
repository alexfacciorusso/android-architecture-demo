package com.alexfacciorusso.architecturedemo.ui.login

import TextInputLayoutMatchers.hasTextInputLayoutErrorText
import android.app.Application
import android.widget.Button
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.alexfacciorusso.architecturedemo.R
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.hamcrest.core.AllOf
import org.hamcrest.core.AllOf.allOf
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginFragmentTest {
    private val viewModel = mockk<LoginViewModel>(relaxed = true)
    private val navController = TestNavHostController(ApplicationProvider.getApplicationContext())


    @Test
    fun sendLogin() {
        launchFragment()

        data class TestCase(
            val givenUsername: String?,
            val givenPassword: String?,
        )

        listOf(
            TestCase(null, null),
            TestCase(null, "password"),
            TestCase("username", null),
            TestCase("username", "password")
        ).forEach { (username, password) ->
            onView(withId(R.id.usernameTextInput)).perform(
                if (username != null) replaceText(username)
                else clearText()
            )

            onView(withId(R.id.passwordTextInput)).perform(
                if (password != null) typeText(password)
                else clearText()
            )

            onView(isAssignableFrom(Button::class.java)).perform(click())

            verify { viewModel.submitLogin(username.orEmpty(), password.orEmpty()) }
        }
    }

    @Test
    fun noNavigationInitially() {
        val mutableLiveData = MutableLiveData<LoginViewState>(LoginViewState.Initial)
        every { viewModel.loggedInState } returns mutableLiveData

        launchFragment()

        navController.currentDestination?.id shouldBe R.id.loginFragment
    }

    @Test
    fun loginSuccess() {
        val mutableLiveData: MutableLiveData<LoginViewState> =
            MutableLiveData(LoginViewState.Initial)
        every { viewModel.loggedInState } returns mutableLiveData

        launchFragment().onFragment {
            mutableLiveData.value = LoginViewState.Success
        }

        navController.currentDestination?.id shouldBe R.id.successFragment

        onUsernameTextInputLayout.check(matches(hasTextInputLayoutErrorText(null)))
        onPasswordTextInputLayout.check(matches(hasTextInputLayoutErrorText(null)))
    }

    @Test
    fun loginFailure() {
        val mutableLiveData: MutableLiveData<LoginViewState> =
            MutableLiveData(LoginViewState.Initial)
        every { viewModel.loggedInState } returns mutableLiveData

        launchFragment().onFragment {
            mutableLiveData.value = LoginViewState.Failure
        }

        navController.currentDestination?.id shouldBe R.id.loginFragment

        val error = ApplicationProvider.getApplicationContext<Application>()
            .getString(R.string.error_invalid)
        onUsernameTextInputLayout.check(matches(hasTextInputLayoutErrorText(error)))
        onPasswordTextInputLayout.check(matches(hasTextInputLayoutErrorText(error)))
    }

    private val onUsernameTextInputLayout
        get() = onView(withId(R.id.usernameInputLayout))

    private val onPasswordTextInputLayout
        get() = onView(withId(R.id.usernameInputLayout))

    private fun launchFragment() =
        launchFragmentInContainer(themeResId = R.style.Theme_ArchitectureDemo) {
            LoginFragment(object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T = viewModel as T
            })
        }.onFragment { fragment ->
            navController.setGraph(R.navigation.main_navigation)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
}