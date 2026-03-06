package com.interrapidisimo.technical.domain.usecase

import com.interrapidisimo.technical.domain.model.User
import com.interrapidisimo.technical.domain.repository.HomeLocalRepository
import javax.inject.Inject

class UserUseCase @Inject constructor(
    private val repository: HomeLocalRepository
) {
    suspend operator fun invoke(): User = repository.getUser()
}