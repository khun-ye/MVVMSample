package com.ytn.mvvm.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ytn.mvvm.data.database.entity.restaurantData
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface restaurantDao {

    @get:Query("select * from tbl_restaurant")
    val allRestaurant: Single<List<restaurantData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllRestaurant(data: List<restaurantData>): Completable
}
