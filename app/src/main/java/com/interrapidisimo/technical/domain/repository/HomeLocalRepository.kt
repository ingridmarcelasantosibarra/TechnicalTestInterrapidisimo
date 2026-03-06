package com.interrapidisimo.technical.domain.repository

import com.interrapidisimo.technical.domain.model.User

interface HomeLocalRepository {
    suspend fun getUser(): User
}