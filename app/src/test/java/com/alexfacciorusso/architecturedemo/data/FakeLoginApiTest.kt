package com.alexfacciorusso.architecturedemo.data

import com.alexfacciorusso.architecturedemo.data.api.FakeLoginApi
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.common.ExperimentalKotest
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldNotBe

@ExperimentalKotest
class FakeLoginApiTest : FunSpec({
    val loginApi = FakeLoginApi()

    test("success") {
        loginApi.login("test", "test") shouldNotBe ""
    }

    context("failure cases") {
        data class UseCase(
            val givenUsername: String,
            val givenPassword: String,
        )

        withData(
            UseCase("", ""),
            UseCase("wrong", "wrong"),
            UseCase("test", "wrong"),
        ) { (givenUsername, givenPassword) ->
            shouldThrow<Exception> {
                loginApi.login(givenUsername, givenPassword).shouldNotBeNull()
            }
        }
    }
})
