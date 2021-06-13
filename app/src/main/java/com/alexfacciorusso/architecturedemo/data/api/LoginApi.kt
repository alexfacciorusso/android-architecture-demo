package com.alexfacciorusso.architecturedemo.data.api

interface LoginApi {
    /**
     * @return A token if the login is correct, else it throws an exception
     */
    suspend fun login(username: String, password: String): String
}