package com.interrapidisimo.technical.data.remote.repository

import com.interrapidisimo.technical.core.network.safeApiCall
import com.interrapidisimo.technical.core.utils.Resource
import com.interrapidisimo.technical.data.remote.api.SecurityApi
import com.interrapidisimo.technical.domain.model.User
import com.interrapidisimo.technical.domain.repository.SecurityRepository
import jakarta.inject.Inject

class SecurityRepositoryImpl @Inject constructor(
    private val securityApi: SecurityApi,
) : SecurityRepository {
    override suspend fun getRemoteVersion(): Resource<String> {
        return safeApiCall { securityApi.getAppVersion() }.let { result ->
            when (result) {
                is Resource.Success -> Resource.Success(result.data.ifEmpty { "0.0.0" })
                is Resource.Error -> result
                is Resource.Loading -> result
            }
        }
    }

    override suspend fun login(): Resource<User> {
        TODO("Not yet implemented")
    }
}