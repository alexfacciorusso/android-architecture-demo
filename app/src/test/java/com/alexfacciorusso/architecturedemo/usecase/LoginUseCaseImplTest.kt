package com.alexfacciorusso.architecturedemo.usecase

import com.alexfacciorusso.architecturedemo.data.repository.LoginRepository
import com.alexfacciorusso.architecturedemo.data.repository.TokenRepository
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk

class LoginUseCaseImplTest : FunSpec({
    val loginRepository = mockk<LoginRepository>()
    val tokenRepository = mockk<TokenRepository>(relaxed = true)

    isolationMode = IsolationMode.InstancePerTest

    val useCase = LoginUseCaseImpl(loginRepository, tokenRepository)

    val token = "token"

    test("login success") {
        val username = "username"
        val password = "password"

        coEvery { loginRepository.login(any(), any()) } returns token

        useCase(username, password) shouldBe LoginResult.Success(token)

        coVerify { loginRepository.login(any(), any()) }
        coVerify { tokenRepository.save(token) }
    }

    test("login failed") {
        val username = "username"
        val password = "password"

        coEvery { loginRepository.login(any(), any()) } returns null

        useCase(username, password) shouldBe LoginResult.Failure

        coVerify { loginRepository.login(any(), any()) }
        coVerify(exactly = 0) { tokenRepository.save(token) }
    }
})
