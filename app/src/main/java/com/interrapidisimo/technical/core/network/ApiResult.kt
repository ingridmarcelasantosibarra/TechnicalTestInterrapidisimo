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
suspend fun <T>safeApiCall(apiCall: suspend ()  -> Response<T>): Resource<T> {
    return try {
        val response = apiCall()
        when {
            response.isSuccessful && response.body() != null -> {
            Resource.Success(response.body()!!)
        }
            response.code() == 401 -> Resource.Error("No autorizado (401)")
            response.code() == 404 -> Resource.Error("Recurso no encontrado (404)")
            response.code() == 500 -> Resource.Error("Error interno del servidor (500)")
            else -> Resource.Error("Error HTTP: ${response.code()} - ${response.message()}")
        }
    } catch (e: HttpException) {
        Resource.Error("Error HTTP: ${e.message()}")
    } catch (e: IOException) {
        Resource.Error("Sin conexión a internet. Verifica tu red")
    } catch (e: Exception) {
        Resource.Error("Error inesperado: ${e.localizedMessage}")
    }
}