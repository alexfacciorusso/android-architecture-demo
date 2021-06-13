package com.alexfacciorusso.architecturedemo.data.repository

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.nulls.beNull
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe

class TokenRepositoryImplTest : FunSpec({
    isolationMode = IsolationMode.InstancePerTest

    val tokenRepository = TokenRepositoryImpl()

    test("initial state") {
        tokenRepository.token.value should beNull()
    }

    test("token saved") {
        val token = "token"

        tokenRepository.save(token)
        tokenRepository.token.value shouldBe token
    }
})
