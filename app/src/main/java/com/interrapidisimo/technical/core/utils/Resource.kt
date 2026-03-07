package com.interrapidisimo.technical.core.utils

/**
 * Wrapper genérico para representar los estados de cualquier operación asíncrona.
 * Loading → Success(data) o Error(message)
 * Nivel capa de dominio
 */
sealed class Resource<out T>{
    data object Loading : Resource<Nothing>()
    data class Success<T>(val data: T) : Resource<T>()
    data class Error(val message: String) : Resource<Nothing>()
}