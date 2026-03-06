package com.interrapidisimo.technical.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.interrapidisimo.technical.data.local.entity.TablaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TableDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tablas: List<TablaEntity>)

    @Query("DELETE FROM tablas")
    suspend fun deleteAll()

    @Transaction
    suspend fun replaceAll(tablas: List<TablaEntity>) {
        deleteAll()
        insertAll(tablas)
    }

    @Query("SELECT * FROM tablas")
    fun getAllTablas(): Flow<List<TablaEntity>>
}