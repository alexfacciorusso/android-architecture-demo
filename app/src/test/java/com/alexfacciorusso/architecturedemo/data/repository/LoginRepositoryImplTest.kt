package com.alexfacciorusso.architecturedemo.data.repository

import com.alexfacciorusso.architecturedemo.data.api.LoginApi
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.nulls.beNull
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk

class LoginRepositoryImplTest : FunSpec({
    isolationMode = IsolationMode.InstancePerTest

    val loginApi = mockk<LoginApi>()

    val loginRepository = LoginRepositoryImpl(loginApi)

    test("success") {
        val token = "token"
        coEvery { loginApi.login(any(), any()) } returns token

        val username = "testUsername"
        val password = "testPassword"

        loginRepository.login(username, password) shouldBe token

        coVerify { loginApi.login(username, password) }
    }

    test("failure") {
        coEvery { loginApi.login(any(), any()) } throws Exception()

        val username = "testUsername"
        val password = "testPassword"

        loginRepository.login(username, password) should beNull()

        coVerify { loginApi.login(username, password) }
    }
})
