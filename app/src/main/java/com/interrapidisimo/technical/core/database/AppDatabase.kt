package com.interrapidisimo.technical.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.interrapidisimo.technical.data.local.dao.UserDao
import com.interrapidisimo.technical.data.local.entity.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    companion object {
        const val DATABASE_NAME = "interrapidisimo_db"
    }
}