package com.interrapidisimo.technical.data.remote.repository

import com.interrapidisimo.technical.core.network.safeApiCall
import com.interrapidisimo.technical.core.utils.Resource
import com.interrapidisimo.technical.data.local.dao.UserDao
import com.interrapidisimo.technical.data.remote.api.SecurityApi
import com.interrapidisimo.technical.data.remote.dto.LoginRequestDto
import com.interrapidisimo.technical.domain.model.User
import com.interrapidisimo.technical.domain.repository.SecurityRepository
import jakarta.inject.Inject

class SecurityRepositoryImpl @Inject constructor(
    private val securityApi: SecurityApi,
    private val userDao: UserDao
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
        val result = safeApiCall {
            securityApi.login(body = LoginRequestDto())
        }
        return when (result) {
            is Resource.Success -> {
                val dto = result.data
                val user = User(
                    usuario = dto.usuario ?: "",
                    identificacion = dto.identificacion ?: "",
                    nombre = dto.nombre ?: ""
                )
                userDao.insertUser(user.toEntity())
                Resource.Success(user)
            }

            is Resource.Error -> result
            is Resource.Loading -> result
        }
    }
}