package com.interrapidisimo.technical.domain.repository

import com.interrapidisimo.technical.core.utils.Resource
import com.interrapidisimo.technical.domain.model.Locality

interface LocalitiesRepository {
    suspend fun getLocalities(): Resource<List<Locality>>
}