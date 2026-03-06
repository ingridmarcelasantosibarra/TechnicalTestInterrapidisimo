package com.interrapidisimo.technical.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey val identificacion: String,
    val usuario: String,
    val nombre: String
)