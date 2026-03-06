package com.interrapidisimo.technical.domain.usecase

import com.interrapidisimo.technical.domain.model.Tabla
import com.interrapidisimo.technical.domain.repository.DataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalTablesUseCase @Inject constructor(
    private val repository: DataRepository
) {
    operator fun invoke(): Flow<List<Tabla>> = repository.getLocalTables()
}