package com.interrapidisimo.technical.domain.repository

import com.interrapidisimo.technical.core.utils.Resource
import com.interrapidisimo.technical.domain.model.Tabla
import kotlinx.coroutines.flow.Flow

interface DataRepository {
    suspend fun fetchAndStoreTables(): Resource<Unit>
    fun getLocalTables(): Flow<List<Tabla>>
}