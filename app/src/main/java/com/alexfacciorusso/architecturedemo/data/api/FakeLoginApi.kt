package com.alexfacciorusso.architecturedemo.data.api

import java.lang.IllegalArgumentException
import javax.inject.Inject

internal class FakeLoginApi @Inject constructor(): LoginApi {
    override suspend fun login(username: String, password: String): String =
        if (username == "test" && password == "test") "my-token-123123"
        else throw IllegalArgumentException("Wrong username or password")
}