package com.interrapidisimo.technical.data.remote.repository

import com.interrapidisimo.technical.core.utils.Resource
import com.interrapidisimo.technical.domain.model.User

interface SecurityRepository {
    suspend fun getRemoteVersion(): Resource<String>
    suspend fun login(): Resource<User>
}