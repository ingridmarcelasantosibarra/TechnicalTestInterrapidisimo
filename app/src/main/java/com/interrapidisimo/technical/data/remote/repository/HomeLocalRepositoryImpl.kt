package com.interrapidisimo.technical.data.remote.repository

import com.interrapidisimo.technical.data.local.dao.UserDao
import com.interrapidisimo.technical.domain.model.User
import com.interrapidisimo.technical.domain.repository.HomeLocalRepository
import jakarta.inject.Inject

class HomeLocalRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : HomeLocalRepository {

    override suspend fun getUser(): User {
        val user = userDao.getUser()
        return user.toDomain()
    }
}