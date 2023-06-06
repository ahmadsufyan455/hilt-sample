package com.zerodev.hiltexample.network

import com.zerodev.hiltexample.model.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiClient {
    @GET("users")
    suspend fun getUsers(): Response<List<User>>
}