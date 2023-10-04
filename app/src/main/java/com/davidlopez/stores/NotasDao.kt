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

    // para contactosEntity**********************************
    @Query("SELECT * FROM NotasEntity where id= :id")
    fun getContactoById(id: Long):NotasEntity

    @Insert
    fun addNota(notasEntity: NotasEntity):Long // contactosEntity :Long

    @Update
    fun updateNota(notasEntity: NotasEntity)

    @Delete
    fun deleteAll(notasEntity: NotasEntity)
}