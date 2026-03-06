package com.interrapidisimo.technical.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TablaDto(
    @SerializedName("NombreTabla") val nombreTabla: String?,
    @SerializedName("BatchSize") val batchSize: Int?,
    @SerializedName("NumeroCampos") val numeroCampos: Int?,
    @SerializedName("Pk") val pk: String?,
    @SerializedName("FechaActualizacionSincro") val fechaActualizacion: String?
)
