package com.interrapidisimo.technical.core.network

import com.interrapidisimo.technical.core.utils.Resource
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

/**
 * Suspended function that wraps a Retrofit call inside a try/catch block,
 * returning a Resource based on the result.
 * Applies the DRY principle: exception handling is centralized here.
 */
suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): ApiResult<T> {
    return try {
        val response = apiCall()
        if (response.isSuccessful && response.body() != null) {
            ApiResult.Success(response.body()!!)
        } else {
            ApiResult.Error(mapHttpError(response.code(), response.message()))
        }
    } catch (e: HttpException) {
        ApiResult.Error("Error HTTP: ${e.message()}")
    } catch (e: IOException) {
        ApiResult.Error("Sin conexión a internet. Verifica tu red")
    } catch (e: Exception) {
        ApiResult.Error("Error inesperado: ${e.localizedMessage}")
    }
}

private fun mapHttpError(code: Int, message: String): String = when (code) {
    401 -> "No autorizado (401)"
    404 -> "Recurso no encontrado (404)"
    500 -> "Error interno del servidor (500)"
    else -> "Error HTTP: $code - $message"
}

sealed interface ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>
    data class Error(val message: String) : ApiResult<Nothing>
}