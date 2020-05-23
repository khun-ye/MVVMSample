package com.ytn.mvvm.data.datasource

import com.ytn.mvvm.data.database.db.yatharDatabase
import com.ytn.mvvm.data.database.entity.restaurantData
import javax.inject.Inject
import io.reactivex.Completable
import io.reactivex.Single

class restaurantLocalDatasource @Inject constructor(private val yatharDatabase: yatharDatabase) {

    val restaurantData: Single<List<restaurantData>>
        get() = yatharDatabase.restaurantDao().allRestaurant

    fun saveRestaurantData(data: List<restaurantData>): Completable {
        return yatharDatabase.restaurantDao().insertAllRestaurant(data)
    }
}
