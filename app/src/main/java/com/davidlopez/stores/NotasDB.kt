package com.davidlopez.stores

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = arrayOf(NotasEntity::class), version = 1)
abstract class NotasDB :RoomDatabase(){
    abstract fun notasDao():NotasDao
}