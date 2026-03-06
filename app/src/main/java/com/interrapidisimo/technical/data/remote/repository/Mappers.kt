package com.interrapidisimo.technical.data.remote.repository

import com.interrapidisimo.technical.data.local.entity.UserEntity
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