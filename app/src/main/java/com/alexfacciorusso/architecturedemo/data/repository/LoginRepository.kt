package com.alexfacciorusso.architecturedemo.data.repository

interface LoginRepository {
    suspend fun login(username: String, password: String): String?
}