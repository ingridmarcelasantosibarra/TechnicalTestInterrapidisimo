package com.interrapidisimo.technical.domain.model

data class Tabla(
    val nombreTabla: String,
    val pk: String,
    val fechaActualizacion: String,
    val batchSize: Int,
    val numeroCampos: Int
)