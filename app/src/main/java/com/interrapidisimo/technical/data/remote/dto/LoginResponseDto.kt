package com.interrapidisimo.technical.data.remote.dto

import com.google.gson.annotations.SerializedName

data class LoginResponseDto(
    @SerializedName("Usuario") val usuario: String?,
    @SerializedName("Identificacion") val identificacion: String?,
    @SerializedName("Nombre") val nombre: String?
)