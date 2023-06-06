package com.zerodev.hiltexample.repository

import com.zerodev.hiltexample.model.User
import com.zerodev.hiltexample.network.ApiClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepository(private val apiClient: ApiClient) {
    fun getUsers(): Flow<List<User>> {
        return flow {
            val response = apiClient.getUsers()
            if (response.isSuccessful) {
                response.body()?.let { emit(it) }
            } else {
                throw Exception("Error: ${response.code()}")
            }
        }
    }
}