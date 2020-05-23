package com.ytn.mvvm.data.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ytn.mvvm.data.database.dao.restaurantDao
import com.ytn.mvvm.data.database.entity.restaurantData

@Database(entities = [restaurantData::class], version = 1, exportSchema = false)
abstract class yatharDatabase : RoomDatabase() {
    abstract fun restaurantDao(): restaurantDao
    companion object{
        var DB_NAME = "yathartest"
    }
}
