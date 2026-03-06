package com.interrapidisimo.technical.domain.usecase

import com.interrapidisimo.technical.core.utils.Resource
import com.interrapidisimo.technical.domain.model.Locality
import com.interrapidisimo.technical.domain.repository.LocalitiesRepository
import javax.inject.Inject

class LocalitiesUseCase @Inject constructor(
    private val repository: LocalitiesRepository
) {
    suspend operator fun invoke(): Resource<List<Locality>> = repository.getLocalities()
}