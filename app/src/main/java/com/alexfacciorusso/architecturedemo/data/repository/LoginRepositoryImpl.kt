package com.alexfacciorusso.architecturedemo.data.repository

import com.alexfacciorusso.architecturedemo.data.api.LoginApi
import javax.inject.Inject

internal class LoginRepositoryImpl @Inject constructor(private val loginApi: LoginApi) :
    LoginRepository {
    override suspend fun login(username: String, password: String): String? =
        try {
            loginApi.login(username, password)
        } catch (_: Exception) {
            null
        }
}