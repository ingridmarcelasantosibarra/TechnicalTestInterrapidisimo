package com.interrapidisimo.technical.data.remote.api

import com.interrapidisimo.technical.core.utils.Constants.AUTHENTICATE_USER_END_POINT
import com.interrapidisimo.technical.core.utils.Constants.VALIDATE_VERSION_END_POINT
import com.interrapidisimo.technical.data.remote.dto.LoginRequestDto
import com.interrapidisimo.technical.data.remote.dto.LoginResponseDto
import com.interrapidisimo.technical.data.remote.dto.VersionDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface SecurityApi {
    @GET(VALIDATE_VERSION_END_POINT)
    suspend fun getAppVersion(): Response<VersionDto>

    @POST(AUTHENTICATE_USER_END_POINT)
    suspend fun login(
        @Header("Usuario") usuario: String = "pam.meredy21",
        @Header("Identificacion") identificacion: String = "987204545",
        @Header("Accept") accept: String = "text/json",
        @Header("IdUsuario") idUsuario: String = "pam.meredy21",
        @Header("IdCentroServicio") idCentroServicio: String = "1295",
        @Header("NombreCentroServicio") nombreCS: String = "PTO/BOGOTA/CUND/COL/OFPRINCIPAL - CRA 30 # 7-45",
        @Header("IdAplicativoOrigen") idAplicativo: String = "9",
        @Header("Content-Type") contentType: String = "application/json",
        @Body body: LoginRequestDto
    ): Response<LoginResponseDto>

}

