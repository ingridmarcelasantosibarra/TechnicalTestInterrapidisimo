package com.interrapidisimo.technical.domain.usecase

import com.interrapidisimo.technical.core.utils.Resource
import com.interrapidisimo.technical.domain.model.User
import com.interrapidisimo.technical.domain.repository.SecurityRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: SecurityRepository
) {
    suspend operator fun invoke(): Resource<User> = repository.login()
}