package com.interrapidisimo.technical.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.interrapidisimo.technical.data.local.dao.TableDao
import com.interrapidisimo.technical.data.local.dao.UserDao
import com.interrapidisimo.technical.data.local.entity.TablaEntity
import com.interrapidisimo.technical.data.local.entity.UserEntity

@Database(
    entities = [UserEntity::class, TablaEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun tableDao(): TableDao
    companion object {
        const val DATABASE_NAME = "interrapidisimo_db"
    }
}