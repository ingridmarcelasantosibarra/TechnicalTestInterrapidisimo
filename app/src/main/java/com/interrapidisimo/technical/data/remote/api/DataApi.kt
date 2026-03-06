package com.interrapidisimo.technical.data.remote.api

import com.interrapidisimo.technical.core.utils.Constants.GET_LOCALITIES_END_POINT
import com.interrapidisimo.technical.core.utils.Constants.GET_SCHEMAS_END_POINT
import com.interrapidisimo.technical.data.remote.dto.LocalidadDto
import com.interrapidisimo.technical.data.remote.dto.TablaDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface DataApi {

    @GET(GET_SCHEMAS_END_POINT)
    suspend fun getTables(
        @Header("Usuario") usuario: String = "pam.meredy21"
    ): Response<List<TablaDto>>

    @GET(GET_LOCALITIES_END_POINT)
    suspend fun getLocalities(): Response<List<LocalidadDto>>
}