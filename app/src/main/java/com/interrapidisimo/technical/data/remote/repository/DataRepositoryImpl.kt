package com.interrapidisimo.technical.data.remote.repository

import com.interrapidisimo.technical.core.network.ApiResult
import com.interrapidisimo.technical.core.network.safeApiCall
import com.interrapidisimo.technical.core.utils.Resource
import com.interrapidisimo.technical.data.local.dao.TableDao
import com.interrapidisimo.technical.data.remote.api.DataApi
import com.interrapidisimo.technical.domain.model.Tabla
import com.interrapidisimo.technical.domain.repository.DataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val dataApi: DataApi,
    private val tablesDao: TableDao
) : DataRepository {
    override suspend fun fetchAndStoreTables(): Resource<Unit> {
        return safeApiCall { dataApi.getTables() }.let { result ->
            when (result) {
                is ApiResult.Success -> {
                    try {
                        val entities = result.data.map { it.toEntity() }
                        tablesDao.replaceAll(entities)
                        Resource.Success(Unit)
                    } catch (e: Exception) {
                        Resource.Error("Error al guardar en base de datos: ${e.localizedMessage}")
                    }
                }
                is ApiResult.Error -> Resource.Error(result.message)
            }
        }    }

    override fun getLocalTables(): Flow<List<Tabla>> {
        return tablesDao.getAllTablas().map { entities ->
            entities.map { it.toDomain() }
        }
    }

}