package com.interrapidisimo.technical.domain.usecase

import com.interrapidisimo.technical.core.utils.Resource
import com.interrapidisimo.technical.domain.repository.DataRepository
import javax.inject.Inject

class FetchTablesUseCase @Inject constructor(
    private val repository: DataRepository
) {
    suspend operator fun invoke(): Resource<Unit> = repository.fetchAndStoreTables()
}