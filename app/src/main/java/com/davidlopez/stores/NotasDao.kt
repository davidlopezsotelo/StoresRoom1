package com.davidlopez.stores

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface NotasDao {
    @Query("SELECT * FROM NotasEntity")
    fun getAllNotas():MutableList<NotasEntity>

    @Insert
    fun addNota(notasEntity: NotasEntity)

    @Update
    fun updateNota(notasEntity: NotasEntity)

    @Delete
    fun deleteAll(notasEntity: NotasEntity)
}