package com.alexfacciorusso.architecturedemo.usecase

interface LoginUseCase {
    suspend operator fun invoke(username: String, password: String): LoginResult
}

sealed interface LoginResult {
    data class Success(val token: String) : LoginResult
    object Failure : LoginResult
}
