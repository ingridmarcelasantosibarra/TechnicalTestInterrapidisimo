package com.interrapidisimo.technical.data.remote.repository

import com.interrapidisimo.technical.core.network.safeApiCall
import com.interrapidisimo.technical.core.utils.Resource
import com.interrapidisimo.technical.data.remote.api.DataApi
import com.interrapidisimo.technical.domain.model.Locality
import com.interrapidisimo.technical.domain.repository.LocalitiesRepository
import javax.inject.Inject

class LocalitiesRepositoryImpl @Inject constructor(
    private val dataApi: DataApi
) : LocalitiesRepository {

    override suspend fun getLocalities(): Resource<List<Locality>> {
        return safeApiCall { dataApi.getLocalities() }.let { result ->
            when (result) {
                is Resource.Success -> {
                    val localities = result.data.map {
                        it.toDomain()
                    }
                    Resource.Success(localities)
                }

                is Resource.Error -> result
                is Resource.Loading -> result
            }
        }
    }
}