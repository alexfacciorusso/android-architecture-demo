package com.alexfacciorusso.architecturedemo.data.repository

import kotlinx.coroutines.flow.StateFlow

interface TokenRepository {
    val token: StateFlow<String?>

    fun save(token: String?)
}