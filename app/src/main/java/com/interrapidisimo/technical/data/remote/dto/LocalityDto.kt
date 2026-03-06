package com.interrapidisimo.technical.data.remote.dto

import com.google.gson.annotations.SerializedName

data class LocalityDto(
    @SerializedName("AbreviacionCiudad") val abreviacionCiudad: String?,
    @SerializedName("NombreCompleto") val nombreCompleto: String?
)