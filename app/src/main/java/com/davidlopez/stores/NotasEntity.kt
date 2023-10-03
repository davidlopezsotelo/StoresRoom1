package com.davidlopez.stores

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NotasEntity")
data class NotasEntity(@PrimaryKey (autoGenerate = true)
                       var id:Long=0,
                       var name:String,
                       var isFaborite:Boolean=false)
