package com.zerodev.hiltexample.utils

import com.zerodev.hiltexample.model.User

object DataDummy {
    fun getUsers(): List<User> {
        return listOf(
            User(
                id = 1,
                login = "mojombo",
                type = "User",
                avatar_url = "https://avatars.githubusercontent.com/u/1?v=4"
            )
        )
    }
}