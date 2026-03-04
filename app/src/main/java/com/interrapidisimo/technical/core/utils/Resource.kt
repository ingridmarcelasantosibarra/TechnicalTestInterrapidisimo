package com.interrapidisimo.technical.core.utils

/**
 * Generic wrapper to represent the states of any asynchronous operation.
 * Loading → Success(data) or Error(message)
 */
sealed class Resource<out T>{

    object Loading : Resource<Nothing>()
    data class Success<T>(val data: T) : Resource<T>()
    data class Error(val message: String) : Resource<Nothing>()
}