package com.alexfacciorusso.architecturedemo.data.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

internal class TokenRepositoryImpl @Inject constructor() : TokenRepository {
    private val _token = MutableStateFlow<String?>(null)
    override val token: StateFlow<String?> = _token.asStateFlow()

    override fun save(token: String?) {
        _token.value = token
    }
}