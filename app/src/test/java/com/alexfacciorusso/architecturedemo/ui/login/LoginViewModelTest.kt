package com.alexfacciorusso.architecturedemo.ui.login

import com.alexfacciorusso.architecturedemo.InstantExecutorListener
import com.alexfacciorusso.architecturedemo.login.LoginViewModel
import com.alexfacciorusso.architecturedemo.login.LoginViewState
import com.alexfacciorusso.architecturedemo.usecase.LoginResult
import com.alexfacciorusso.architecturedemo.usecase.LoginUseCase
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first

@ExperimentalCoroutinesApi
class LoginViewModelTest : BehaviorSpec({
    listener(InstantExecutorListener())
    isolationMode = IsolationMode.InstancePerLeaf

    val loginUseCase = mockk<LoginUseCase>()

    Given("a viewmodel under test") {
        val viewModel = LoginViewModel(loginUseCase)

        val token = "token"
        val username = "username"
        val password = "password"

        And("a successful login") {
            val success = LoginResult.Success(token)
            coEvery { loginUseCase(any(), any()) } returns success

            When("the login is submitted") {
                viewModel.submitLogin(username, password)

                Then("the usecase should be called") {
                    coVerify { loginUseCase(username, password) }
                }

                Then("a logged in event should be sent") {
                    viewModel.loggedInState.first() shouldBe LoginViewState.Success
                }

                And("the success state is used"){
                    viewModel.useSuccess()

                    Then("the state should be returned to initial"){
                        viewModel.loggedInState.first() shouldBe LoginViewState.Initial
                    }
                }
            }
        }

        And("a failed login") {
            coEvery { loginUseCase(any(), any()) } returns LoginResult.Failure

            When("the login is submitted") {
                viewModel.submitLogin(username, password)

                Then("the usecase should be called") {
                    coVerify { loginUseCase(username, password) }
                }

                Then("a logged in event should be sent") {
                    viewModel.loggedInState.first() shouldBe LoginViewState.Failure
                }
            }
        }
    }
})
