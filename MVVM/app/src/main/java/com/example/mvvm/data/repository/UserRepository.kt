package com.example.mvvm.data.repository

import com.example.mvvm.data.local.dao.UserDao
import com.example.mvvm.data.local.entity.User

class UserRepository(private val dao: UserDao) {

    suspend fun insert(user: User) {
        dao.insert(user)
    }

    suspend fun login(username: String, password: String): User? {
        return dao.login(username, password)
    }
}
