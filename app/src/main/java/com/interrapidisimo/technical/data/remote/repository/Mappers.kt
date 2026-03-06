package com.interrapidisimo.technical.data.remote.repository

import com.interrapidisimo.technical.data.local.entity.TablaEntity
import com.interrapidisimo.technical.data.local.entity.UserEntity
import com.interrapidisimo.technical.data.remote.dto.LocalityDto
import com.interrapidisimo.technical.data.remote.dto.TablaDto
import com.interrapidisimo.technical.domain.model.Locality
import com.interrapidisimo.technical.domain.model.Tabla
import com.interrapidisimo.technical.domain.model.User

fun User.toEntity() = UserEntity(
    identificacion = identificacion,
    usuario = usuario,
    nombre = nombre
)

fun UserEntity.toDomain() = User(
    usuario = usuario,
    identificacion = identificacion,
    nombre = nombre
)

fun TablaDto.toEntity() = TablaEntity(
    nombreTabla = nombreTabla ?: "",
    pk = pk ?: "",
    fechaActualizacion = fechaActualizacion ?: "",
    batchSize = batchSize ?: 0,
    numeroCampos = numeroCampos ?: 0,
)

fun TablaEntity.toDomain() = Tabla(
    nombreTabla = nombreTabla,
    pk = pk,
    fechaActualizacion = fechaActualizacion,
    batchSize = batchSize,
    numeroCampos = numeroCampos
)

fun LocalityDto.toDomain() = Locality(
    abreviacion = abreviacionCiudad ?: "",
    nombreCompleto = nombreCompleto ?: ""
)