package com.alexfacciorusso.architecturedemo.usecase

import com.alexfacciorusso.architecturedemo.data.repository.LoginRepository
import com.alexfacciorusso.architecturedemo.data.repository.TokenRepository
import javax.inject.Inject

internal class LoginUseCaseImpl @Inject constructor(
    private val loginRepository: LoginRepository,
    private val tokenRepository: TokenRepository,
) : LoginUseCase {
    override suspend fun invoke(username: String, password: String): LoginResult =
        loginRepository.login(username, password)?.let { token ->
            tokenRepository.save(token)
            LoginResult.Success(token)
        } ?: LoginResult.Failure
}