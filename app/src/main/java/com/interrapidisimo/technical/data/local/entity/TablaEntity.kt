package com.interrapidisimo.technical.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tablas")
data class TablaEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombreTabla: String,
    val pk: String,
    val fechaActualizacion: String,
    val batchSize: Int,
    val numeroCampos: Int
)